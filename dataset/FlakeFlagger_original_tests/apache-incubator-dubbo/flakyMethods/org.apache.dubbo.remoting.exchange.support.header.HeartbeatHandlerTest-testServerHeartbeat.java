@Test public void testServerHeartbeat() throws Exception {
  URL serverURL=URL.valueOf("header://localhost:55555?transporter=netty3");
  serverURL=serverURL.addParameter(Constants.HEARTBEAT_KEY,1000);
  TestHeartbeatHandler handler=new TestHeartbeatHandler();
  server=Exchangers.bind(serverURL,handler);
  System.out.println("Server bind successfully");
  FakeChannelHandlers.setTestingChannelHandlers();
  serverURL=serverURL.removeParameter(Constants.HEARTBEAT_KEY);
  client=Exchangers.connect(serverURL);
  Thread.sleep(10000);
  Assert.assertTrue(handler.disconnectCount > 0);
  System.out.println("disconnect count " + handler.disconnectCount);
}
