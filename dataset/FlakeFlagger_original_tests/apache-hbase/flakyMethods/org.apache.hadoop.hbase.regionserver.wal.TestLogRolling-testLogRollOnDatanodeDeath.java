/** 
 * Tests that logs are rolled upon detecting datanode death Requires an HDFS jar with HDFS-826 & syncFs() support (HDFS-200)
 * @throws Exception
 */
public void testLogRollOnDatanodeDeath() throws Exception {
  assertTrue("This test requires HLog file replication.",fs.getDefaultReplication() > 1);
  new HTable(conf,HConstants.META_TABLE_NAME);
  this.server=cluster.getRegionServer(0);
  this.log=server.getLog();
  assertTrue("Need HDFS-826 for this test",log.canGetCurReplicas());
  assertTrue("Need append support for this test",HLog.isAppend(conf));
  dfsCluster.startDataNodes(conf,1,true,null,null);
  dfsCluster.waitActive();
  assertTrue(dfsCluster.getDataNodes().size() >= fs.getDefaultReplication() + 1);
  String tableName=getName();
  HTableDescriptor desc=new HTableDescriptor(tableName);
  desc.addFamily(new HColumnDescriptor(HConstants.CATALOG_FAMILY));
  HBaseAdmin admin=new HBaseAdmin(conf);
  admin.createTable(desc);
  HTable table=new HTable(conf,tableName);
  table.setAutoFlush(true);
  long curTime=System.currentTimeMillis();
  long oldFilenum=log.getFilenum();
  assertTrue("Log should have a timestamp older than now",curTime > oldFilenum && oldFilenum != -1);
  writeData(table,1);
  assertTrue("The log shouldn't have rolled yet",oldFilenum == log.getFilenum());
  OutputStream stm=log.getOutputStream();
  Method getPipeline=null;
  for (  Method m : stm.getClass().getDeclaredMethods()) {
    if (m.getName().endsWith("getPipeline")) {
      getPipeline=m;
      getPipeline.setAccessible(true);
      break;
    }
  }
  assertTrue("Need DFSOutputStream.getPipeline() for this test",getPipeline != null);
  Object repl=getPipeline.invoke(stm,new Object[]{});
  DatanodeInfo[] pipeline=(DatanodeInfo[])repl;
  assertTrue(pipeline.length == fs.getDefaultReplication());
  DataNodeProperties dnprop=dfsCluster.stopDataNode(pipeline[0].getName());
  assertTrue(dnprop != null);
  writeData(table,2);
  long newFilenum=log.getFilenum();
  assertTrue("Missing datanode should've triggered a log roll",newFilenum > oldFilenum && newFilenum > curTime);
  writeData(table,3);
  assertTrue("The log should not roll again.",log.getFilenum() == newFilenum);
  assertTrue("New log file should have the default replication",log.getLogReplication() == fs.getDefaultReplication());
}
