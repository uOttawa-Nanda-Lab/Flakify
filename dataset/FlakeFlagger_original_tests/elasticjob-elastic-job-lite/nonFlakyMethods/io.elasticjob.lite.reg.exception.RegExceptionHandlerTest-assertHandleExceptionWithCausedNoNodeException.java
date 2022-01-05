@Test public void assertHandleExceptionWithCausedNoNodeException(){
  RegExceptionHandler.handleException(new RuntimeException(new NoNodeException()));
}
