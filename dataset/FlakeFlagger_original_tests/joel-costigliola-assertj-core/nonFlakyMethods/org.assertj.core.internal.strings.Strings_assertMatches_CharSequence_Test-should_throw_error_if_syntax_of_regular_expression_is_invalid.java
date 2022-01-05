@Test public void should_throw_error_if_syntax_of_regular_expression_is_invalid(){
  thrown.expect(PatternSyntaxException.class);
  strings.assertMatches(someInfo(),actual,"*...");
}
