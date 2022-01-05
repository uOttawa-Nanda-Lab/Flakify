@Test public void should_throw_error_if_regular_expression_is_null(){
  thrown.expectNullPointerException(regexPatternIsNull());
  String regex=null;
  strings.assertMatches(someInfo(),actual,regex);
}
