@Test public void lonelyClientTest() throws ExecutionException, InterruptedException {
  OrbitStage client=createClient();
  ISomeActor player=client.getReference(ISomeActor.class,"232");
  client.getHosting().setTimeToWaitForServersMillis(100);
  expectException(() -> player.sayHello("meh"));
}
