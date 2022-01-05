@Test(expected=IllegalArgumentException.class) public void breakItAgain() throws ExecutionException, InterruptedException {
  OrbitStage stage1=createStage();
  stage1.getReference(ISingularThing.class,"aaa");
}
