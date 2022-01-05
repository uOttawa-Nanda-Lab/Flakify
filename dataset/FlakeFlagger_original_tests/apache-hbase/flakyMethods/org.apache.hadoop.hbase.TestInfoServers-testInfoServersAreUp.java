/** 
 * @throws Exception
 */
public void testInfoServersAreUp() throws Exception {
  new HTable(conf,".META.");
  int port=cluster.getMaster().getInfoServer().getPort();
  assertHasExpectedContent(new URL("http://localhost:" + port + "/index.html"),"master");
  port=cluster.getRegionServerThreads().get(0).getRegionServer().getInfoServer().getPort();
  assertHasExpectedContent(new URL("http://localhost:" + port + "/index.html"),"regionserver");
}
