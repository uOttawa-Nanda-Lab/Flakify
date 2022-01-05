@Test public void referencePassingTest() throws ExecutionException, InterruptedException {
  OrbitStage stage1=createStage();
  ISomeMatch someMatch=stage1.getReference(ISomeMatch.class,"300");
  ISomePlayer somePlayer=stage1.getReference(ISomePlayer.class,"101");
  someMatch.addPlayer(somePlayer).get();
}
