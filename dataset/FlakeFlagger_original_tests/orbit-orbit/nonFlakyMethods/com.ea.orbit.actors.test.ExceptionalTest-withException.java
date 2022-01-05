@Test(expected=CompletionException.class) public void withException() throws ExecutionException, InterruptedException {
  OrbitStage stage1=createStage();
  final IExceptionalThing ref=stage1.getReference(IExceptionalThing.class,"0");
  ref.justThrowAnException().join();
}
