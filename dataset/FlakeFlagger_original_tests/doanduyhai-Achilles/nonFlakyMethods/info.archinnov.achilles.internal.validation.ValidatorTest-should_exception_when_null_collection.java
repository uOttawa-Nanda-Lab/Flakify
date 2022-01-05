@Test(expected=AchillesException.class) public void should_exception_when_null_collection() throws Exception {
  Validator.validateNotEmpty((Collection<String>)null,"arg");
}
