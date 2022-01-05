@Test(expected=AchillesException.class) public void should_exception_when_blank() throws Exception {
  Validator.validateNotBlank("","arg");
}
