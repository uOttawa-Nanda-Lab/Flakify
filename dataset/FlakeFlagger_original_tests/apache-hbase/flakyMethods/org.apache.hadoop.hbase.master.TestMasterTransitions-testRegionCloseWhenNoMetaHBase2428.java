/** 
 * In 2428, the meta region has just been set offline and then a close comes in.
 * @see <a href="https://issues.apache.org/jira/browse/HBASE-2428">HBASE-2428</a> 
 */
@Test(timeout=300000) public void testRegionCloseWhenNoMetaHBase2428() throws Exception {
  LOG.info("Running testRegionCloseWhenNoMetaHBase2428");
  MiniHBaseCluster cluster=TEST_UTIL.getHBaseCluster();
  final HMaster master=cluster.getMaster();
  int metaIndex=cluster.getServerWithMeta();
  int otherServerIndex=-1;
  for (int i=0; i < cluster.getRegionServerThreads().size(); i++) {
    if (i == metaIndex)     continue;
    otherServerIndex=i;
    break;
  }
  final HRegionServer otherServer=cluster.getRegionServer(otherServerIndex);
  final HRegionServer metaHRS=cluster.getRegionServer(metaIndex);
  final HRegionInfo hri=otherServer.getOnlineRegions().iterator().next().getRegionInfo();
  HBase2428Listener listener=new HBase2428Listener(cluster,metaHRS.getHServerInfo().getServerAddress(),hri,otherServerIndex);
  master.getRegionServerOperationQueue().registerRegionServerOperationListener(listener);
  try {
    cluster.abortRegionServer(metaIndex);
    while (!listener.metaShutdownReceived)     Threads.sleep(100);
    while (!listener.isDone())     Threads.sleep(10);
    assertTrue(listener.getCloseCount() > 1);
    assertTrue(listener.getCloseCount() < ((HBase2428Listener.SERVER_DURATION / HBase2428Listener.CLOSE_DURATION) * 2));
    assertRegionIsBackOnline(hri);
  }
  finally {
    master.getRegionServerOperationQueue().unregisterRegionServerOperationListener(listener);
  }
}
