@Test public void checkingTheException() throws ExecutionException, InterruptedException {
  OrbitStage stage1=createStage();
  final IExceptionalThing ref=stage1.getReference(IExceptionalThing.class,"0");
  final Task<String> fut=ref.justThrowAnException();
  final Throwable ex=fut.handle((r,e) -> e.getCause()).join();
  assertTrue(fut.isCompletedExceptionally());
  assertTrue(ex instanceof RuntimeException);
  assertEquals("exception!",ex.getMessage());
}
