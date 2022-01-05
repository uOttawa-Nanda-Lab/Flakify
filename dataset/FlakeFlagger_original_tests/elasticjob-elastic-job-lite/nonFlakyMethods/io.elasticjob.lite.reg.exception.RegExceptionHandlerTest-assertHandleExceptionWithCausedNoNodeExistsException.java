@Test public void assertHandleExceptionWithCausedNoNodeExistsException(){
  RegExceptionHandler.handleException(new RuntimeException(new NodeExistsException()));
}
