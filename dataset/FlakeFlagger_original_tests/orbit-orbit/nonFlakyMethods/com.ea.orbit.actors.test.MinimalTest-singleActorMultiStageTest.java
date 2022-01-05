@Test public void singleActorMultiStageTest() throws ExecutionException, InterruptedException {
  for (int i=0; i < 10; i++) {
    OrbitStage stage=createStage();
    ISomeActor someActor=stage.getReference(ISomeActor.class,"1");
    assertEquals("bla",someActor.sayHello("bla").get());
  }
}
