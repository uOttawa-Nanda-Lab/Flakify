/** 
 * Test adding in a new server before old one on same host+port is dead. Make the test more onerous by having the server under test carry the meta. If confusion between old and new, purportedly meta never comes back.  Test that meta gets redeployed.
 */
@Test(timeout=300000) public void testAddingServerBeforeOldIsDead2413() throws IOException {
  LOG.info("Running testAddingServerBeforeOldIsDead2413");
  MiniHBaseCluster cluster=TEST_UTIL.getHBaseCluster();
  int count=count();
  int metaIndex=cluster.getServerWithMeta();
  MiniHBaseClusterRegionServer metaHRS=(MiniHBaseClusterRegionServer)cluster.getRegionServer(metaIndex);
  int port=metaHRS.getServerInfo().getServerAddress().getPort();
  Configuration c=TEST_UTIL.getConfiguration();
  String oldPort=c.get(HConstants.REGIONSERVER_PORT,"0");
  try {
    LOG.info("KILLED=" + metaHRS);
    metaHRS.kill();
    c.set(HConstants.REGIONSERVER_PORT,Integer.toString(port));
    HRegionServer hrs=null;
    while (true) {
      try {
        hrs=cluster.startRegionServer().getRegionServer();
        break;
      }
 catch (      IOException e) {
        if (e.getCause() != null && e.getCause() instanceof InvocationTargetException) {
          InvocationTargetException ee=(InvocationTargetException)e.getCause();
          if (ee.getCause() != null && ee.getCause() instanceof BindException) {
            LOG.info("BindException; retrying: " + e.toString());
          }
        }
      }
    }
    LOG.info("STARTED=" + hrs);
    while (hrs.getOnlineRegions().size() < 3)     Threads.sleep(100);
    LOG.info(hrs.toString() + " has " + hrs.getOnlineRegions().size()+ " regions");
    assertEquals(count,count());
  }
  finally {
    c.set(HConstants.REGIONSERVER_PORT,oldPort);
  }
}
