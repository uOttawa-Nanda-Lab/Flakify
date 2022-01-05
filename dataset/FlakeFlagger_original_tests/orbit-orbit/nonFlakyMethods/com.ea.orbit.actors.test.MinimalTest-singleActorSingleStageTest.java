@Test public void singleActorSingleStageTest() throws ExecutionException, InterruptedException {
  OrbitStage stage1=createStage();
  ISomeActor someActor=stage1.getReference(ISomeActor.class,"1");
  assertEquals("bla",someActor.sayHello("bla").get());
}
