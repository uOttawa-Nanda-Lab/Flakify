/** 
 * Create a Store with the result of a HLog split and test we only see the good edits
 * @throws Exception
 */
@Test public void runReconstructionLog() throws Exception {
  byte[] family=Bytes.toBytes("column");
  HColumnDescriptor hcd=new HColumnDescriptor(family);
  HTableDescriptor htd=new HTableDescriptor(TABLE);
  htd.addFamily(hcd);
  HRegionInfo info=new HRegionInfo(htd,null,null,false);
  Path oldLogDir=new Path(this.dir,HConstants.HREGION_OLDLOGDIR_NAME);
  HLog log=new HLog(cluster.getFileSystem(),this.dir,oldLogDir,conf,null);
  HRegion region=new HRegion(dir,log,cluster.getFileSystem(),conf,info,null);
  List<KeyValue> result=new ArrayList<KeyValue>();
  NavigableSet<byte[]> qualifiers=new ConcurrentSkipListSet<byte[]>(Bytes.BYTES_COMPARATOR);
  final byte[] tableName=Bytes.toBytes(TABLE);
  final byte[] rowName=tableName;
  final byte[] regionName=info.getRegionName();
  for (int j=0; j < TOTAL_EDITS; j++) {
    byte[] qualifier=Bytes.toBytes(Integer.toString(j));
    byte[] column=Bytes.toBytes("column:" + Integer.toString(j));
    WALEdit edit=new WALEdit();
    edit.add(new KeyValue(rowName,family,qualifier,System.currentTimeMillis(),column));
    log.append(info,tableName,edit,System.currentTimeMillis());
  }
  long logSeqId=log.startCacheFlush();
  log.completeCacheFlush(regionName,tableName,logSeqId,info.isMetaRegion());
  WALEdit edit=new WALEdit();
  edit.add(new KeyValue(rowName,Bytes.toBytes("another family"),rowName,System.currentTimeMillis(),rowName));
  log.append(info,tableName,edit,System.currentTimeMillis());
  log.sync();
  log.close();
  List<Path> splits=HLog.splitLog(new Path(conf.get(HConstants.HBASE_DIR)),this.dir,oldLogDir,cluster.getFileSystem(),conf);
  assertEquals(1,splits.size());
  assertTrue(cluster.getFileSystem().exists(splits.get(0)));
  Store store=new Store(dir,region,hcd,cluster.getFileSystem(),splits.get(0),conf,null);
  Get get=new Get(rowName);
  store.get(get,qualifiers,result);
  assertEquals(TOTAL_EDITS,result.size());
}
