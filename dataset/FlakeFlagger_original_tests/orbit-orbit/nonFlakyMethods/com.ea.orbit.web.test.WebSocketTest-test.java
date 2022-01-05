@Test public void test() throws URISyntaxException, IOException, DeploymentException, InterruptedException {
  final OrbitContainer container=new OrbitContainer();
  final int port=NetUtils.findFreePort();
  container.setProperties(Collections.singletonMap("orbit.http.port",String.valueOf(port)));
  container.start();
  final AClientEndpoint client=new AClientEndpoint();
  WebSocketContainer socketContainer=ContainerProvider.getWebSocketContainer();
  socketContainer.connectToServer(client,new URI("ws://localhost:" + port + "/echo"));
  client.session.getBasicRemote().sendText("test1");
  client.session.getAsyncRemote().sendText("test2");
  assertEquals("test1",client.messages.poll(10,TimeUnit.SECONDS));
  assertEquals("test2",client.messages.poll(10,TimeUnit.SECONDS));
  client.session.close();
  container.stop();
}
