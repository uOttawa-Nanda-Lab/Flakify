@Test(expected=AchillesException.class) public void should_exception_when_null_map() throws Exception {
  Validator.validateNotEmpty((Map<String,String>)null,"arg");
}
