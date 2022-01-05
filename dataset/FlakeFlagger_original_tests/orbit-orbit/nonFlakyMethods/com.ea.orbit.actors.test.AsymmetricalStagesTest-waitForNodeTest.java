@Test public void waitForNodeTest() throws ExecutionException, InterruptedException, NoSuchFieldException, IllegalAccessException {
  OrbitStage stage1=createStage(SomeActor.class,SomePlayer.class);
  final Task<String> test=stage1.getReference(ISomeMatch.class,"100").getNodeId();
  OrbitStage stage2=createStage(SomeActor.class,SomeMatch.class);
  assertNotNull(test.join());
}
