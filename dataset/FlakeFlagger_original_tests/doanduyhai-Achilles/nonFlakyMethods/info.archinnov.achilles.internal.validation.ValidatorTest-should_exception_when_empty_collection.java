@Test(expected=AchillesException.class) public void should_exception_when_empty_collection() throws Exception {
  Validator.validateNotEmpty(new ArrayList<String>(),"arg");
}
