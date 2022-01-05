@Test public void noException() throws ExecutionException, InterruptedException {
  OrbitStage stage1=createStage();
  final IExceptionalThing ref=stage1.getReference(IExceptionalThing.class,"0");
  assertEquals("resp",ref.justRespond().join());
}
