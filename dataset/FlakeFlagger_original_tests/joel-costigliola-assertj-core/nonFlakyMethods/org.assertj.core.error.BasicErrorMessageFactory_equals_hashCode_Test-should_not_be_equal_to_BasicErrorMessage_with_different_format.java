@Test public void should_not_be_equal_to_BasicErrorMessage_with_different_format(){
  assertFalse(factory.equals(new BasicErrorMessageFactory("How are you, %s?","Yoda")));
}
