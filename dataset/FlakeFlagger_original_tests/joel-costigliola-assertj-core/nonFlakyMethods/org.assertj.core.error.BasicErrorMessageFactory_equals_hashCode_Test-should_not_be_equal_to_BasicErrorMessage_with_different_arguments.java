@Test public void should_not_be_equal_to_BasicErrorMessage_with_different_arguments(){
  assertFalse(factory.equals(new BasicErrorMessageFactory("Hello %s","Luke")));
}
