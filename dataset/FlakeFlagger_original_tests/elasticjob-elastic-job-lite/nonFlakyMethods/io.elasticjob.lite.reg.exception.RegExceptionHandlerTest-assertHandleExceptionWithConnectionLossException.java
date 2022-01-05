@Test public void assertHandleExceptionWithConnectionLossException(){
  RegExceptionHandler.handleException(new ConnectionLossException());
}
