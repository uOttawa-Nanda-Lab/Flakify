@Test(expected=AchillesException.class) public void should_exception_when_null() throws Exception {
  Validator.validateNotNull(null,"arg");
}
