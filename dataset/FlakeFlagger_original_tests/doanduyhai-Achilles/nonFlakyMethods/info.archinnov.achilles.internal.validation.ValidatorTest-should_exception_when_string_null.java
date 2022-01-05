@Test(expected=AchillesException.class) public void should_exception_when_string_null() throws Exception {
  Validator.validateNotBlank(null,"arg");
}
