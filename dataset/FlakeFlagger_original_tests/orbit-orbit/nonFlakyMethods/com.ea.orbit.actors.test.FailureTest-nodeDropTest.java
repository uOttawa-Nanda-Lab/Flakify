@Test public void nodeDropTest() throws ExecutionException, InterruptedException {
  OrbitStage stage1=createStage();
  OrbitStage stage2=createStage();
  ISomeActor someActor=stage1.getReference(ISomeActor.class,"1");
  UUID uuid=someActor.getUniqueActivationId().get();
  assertEquals("bla",someActor.sayHello("bla").get());
  OrbitStage stage3=createStage();
  OrbitStage stage4=createStage();
  ISomeActor someActor_r3=stage3.getReference(ISomeActor.class,"1");
  assertEquals(uuid,someActor_r3.getUniqueActivationId().get());
  stage1.stop();
  stage2.stop();
  final UUID secondUUID=someActor_r3.getUniqueActivationId().get();
  assertNotEquals(uuid,secondUUID);
  ISomeActor someActor_r4=stage4.getReference(ISomeActor.class,"1");
  assertEquals(secondUUID,someActor_r4.getUniqueActivationId().get());
  stage3.stop();
  stage4.stop();
}
