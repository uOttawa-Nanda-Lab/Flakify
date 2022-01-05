@Test public void should_throw_error_if_Pattern_is_null(){
  thrown.expectNullPointerException(regexPatternIsNull());
  Pattern pattern=null;
  strings.assertMatches(someInfo(),actual,pattern);
}
