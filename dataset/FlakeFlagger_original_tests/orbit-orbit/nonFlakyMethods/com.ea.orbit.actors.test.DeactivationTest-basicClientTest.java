@Test public void basicClientTest() throws ExecutionException, InterruptedException {
  OrbitStage stage=createStage();
  OrbitStage client=createClient();
  ISomeActor player=client.getReference(ISomeActor.class,"232");
  assertEquals("bla",player.sayHello("meh").get());
}
