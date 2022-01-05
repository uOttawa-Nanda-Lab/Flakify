@Test public void ensureUniqueActivation() throws ExecutionException, InterruptedException {
  OrbitStage stage0=createStage();
  UUID uuid=stage0.getReference(ISomeActor.class,"1").getUniqueActivationId().get();
  for (int i=0; i < 10; i++) {
    OrbitStage stage=createStage();
    ISomeActor someActor=stage.getReference(ISomeActor.class,"1");
    assertEquals("bla",someActor.sayHello("bla").get());
    assertEquals(uuid,someActor.getUniqueActivationId().get());
  }
}
