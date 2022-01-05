@Test(expected=AchillesException.class) public void should_exception_when_empty_map() throws Exception {
  Validator.validateNotEmpty(new HashMap<String,String>(),"arg");
}
