@Test public void should_fail_if_actual_is_not_null_and_expected_is_null(){
  thrown.expectNullPointerException(charSequenceToLookForIsNull());
  strings.assertEqualsIgnoringWhitespace(someInfo(),"Luke",null);
}
