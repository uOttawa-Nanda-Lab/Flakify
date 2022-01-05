@Test public void testClientHeartbeat() throws Exception {
  FakeChannelHandlers.setTestingChannelHandlers();
  URL serverURL=URL.valueOf("header://localhost:55555?transporter=netty3");
  TestHeartbeatHandler handler=new TestHeartbeatHandler();
  server=Exchangers.bind(serverURL,handler);
  System.out.println("Server bind successfully");
  FakeChannelHandlers.resetChannelHandlers();
  serverURL=serverURL.addParameter(Constants.HEARTBEAT_KEY,1000);
  client=Exchangers.connect(serverURL);
  Thread.sleep(10000);
  Assert.assertTrue(handler.connectCount > 0);
  System.out.println("connect count " + handler.connectCount);
}
