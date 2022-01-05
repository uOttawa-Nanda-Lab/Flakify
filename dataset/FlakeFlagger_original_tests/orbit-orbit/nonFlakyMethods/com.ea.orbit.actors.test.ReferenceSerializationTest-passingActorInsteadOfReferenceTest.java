@Test public void passingActorInsteadOfReferenceTest() throws ExecutionException, InterruptedException {
  OrbitStage stage1=createStage();
  ISomeMatch someMatch=stage1.getReference(ISomeMatch.class,"300");
  ISomePlayer somePlayer=stage1.getReference(ISomePlayer.class,"101");
  somePlayer.joinMatch(someMatch).get();
}
