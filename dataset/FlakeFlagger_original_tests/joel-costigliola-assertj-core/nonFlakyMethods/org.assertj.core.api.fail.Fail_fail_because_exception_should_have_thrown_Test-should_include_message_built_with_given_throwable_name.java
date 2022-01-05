@Test public void should_include_message_built_with_given_throwable_name(){
  thrown.expectAssertionError("Expected OutOfMemoryError to be thrown");
  shouldHaveThrown(OutOfMemoryError.class);
}
