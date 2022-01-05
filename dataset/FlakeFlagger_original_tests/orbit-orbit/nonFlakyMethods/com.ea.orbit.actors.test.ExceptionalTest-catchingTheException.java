@Test public void catchingTheException() throws ExecutionException, InterruptedException {
  OrbitStage stage1=createStage();
  final IExceptionalThing ref=stage1.getReference(IExceptionalThing.class,"0");
  try {
    ref.justThrowAnException().join();
    fail("should have thrown an exception");
  }
 catch (  CompletionException ex) {
    assertTrue(ex.getCause() instanceof RuntimeException);
    assertEquals("exception!",ex.getCause().getMessage());
  }
}
