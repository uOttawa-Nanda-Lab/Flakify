@Test(expected=RegException.class) public void assertHandleExceptionWithOtherException(){
  RegExceptionHandler.handleException(new RuntimeException());
}
