@Test public void cleanupTest() throws ExecutionException, InterruptedException {
  OrbitStage stage=createStage();
  OrbitStage client=createClient();
  ISomeActor actor1=client.getReference(ISomeActor.class,"1000");
  final Set<UUID> set=new HashSet<>();
  for (int i=0; i < 25; i++) {
    set.add(actor1.getUniqueActivationId().get());
  }
  assertEquals(1,set.size());
  stage.cleanup(true);
  set.add(actor1.getUniqueActivationId().get());
  assertEquals(1,set.size());
  clock.incrementTimeMillis(TimeUnit.MINUTES.toMillis(20));
  stage.cleanup(true);
  set.add(actor1.getUniqueActivationId().get());
  assertEquals(2,set.size());
}
