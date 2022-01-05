@Test public void should_add_stack_trace_of_current_thread(){
  try {
    throw new AssertJThrowable();
  }
 catch (  AssertJThrowable throwable) {
    assertTrue(hasStackTraceElementContainingAssertJClass(throwable));
    Throwables.removeAssertJRelatedElementsFromStackTrace(throwable);
    assertFalse(hasStackTraceElementContainingAssertJClass(throwable));
  }
}
