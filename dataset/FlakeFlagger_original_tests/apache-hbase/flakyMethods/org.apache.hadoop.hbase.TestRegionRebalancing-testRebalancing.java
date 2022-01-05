/** 
 * For HBASE-71. Try a few different configurations of starting and stopping region servers to see if the assignment or regions is pretty balanced.
 * @throws IOException
 */
public void testRebalancing() throws IOException {
  table=new HTable(conf,"test");
  assertEquals("Test table should have 20 regions",20,table.getStartKeys().length);
  assertRegionsAreBalanced();
  LOG.debug("Adding 2nd region server.");
  cluster.startRegionServer();
  assertRegionsAreBalanced();
  LOG.debug("Adding 3rd region server.");
  cluster.startRegionServer();
  assertRegionsAreBalanced();
  LOG.debug("Killing the 3rd region server.");
  cluster.stopRegionServer(2,false);
  cluster.waitOnRegionServer(2);
  assertRegionsAreBalanced();
  LOG.debug("Adding 3rd region server");
  cluster.startRegionServer();
  LOG.debug("Adding 4th region server");
  cluster.startRegionServer();
  assertRegionsAreBalanced();
  for (int i=0; i < 6; i++) {
    LOG.debug("Adding " + (i + 5) + "th region server");
    cluster.startRegionServer();
  }
  assertRegionsAreBalanced();
}
