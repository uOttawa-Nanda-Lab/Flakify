@Test public void assertHandleExceptionWithCausedConnectionLossException(){
  RegExceptionHandler.handleException(new RuntimeException(new ConnectionLossException()));
}
