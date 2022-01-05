@Test public void assertHandleExceptionWithNoNodeExistsException(){
  RegExceptionHandler.handleException(new NodeExistsException());
}
