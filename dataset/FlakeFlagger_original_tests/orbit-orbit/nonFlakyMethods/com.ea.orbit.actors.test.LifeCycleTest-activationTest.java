@Test public void activationTest() throws ExecutionException, InterruptedException {
  OrbitStage stage=createStage();
  OrbitStage client=createClient();
  ISomeActor actor1=client.getReference(ISomeActor.class,"1000");
  assertTrue(actor1.getActivationWasCalled().get());
}
