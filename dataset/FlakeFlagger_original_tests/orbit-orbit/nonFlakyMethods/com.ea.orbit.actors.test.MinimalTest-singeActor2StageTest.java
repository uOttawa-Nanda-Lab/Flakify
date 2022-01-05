@Test public void singeActor2StageTest() throws ExecutionException, InterruptedException {
  for (int i=0; i < 2; i++) {
    OrbitStage stage=createStage();
    ISomeActor someActor=stage.getReference(ISomeActor.class,"1");
    assertEquals("bla",someActor.sayHello("bla").get());
  }
}
