@Test public void should_include_message_built_with_given_exception_name(){
  thrown.expectAssertionError("Expected NullPointerException to be thrown");
  failBecauseExceptionWasNotThrown(NullPointerException.class);
}
