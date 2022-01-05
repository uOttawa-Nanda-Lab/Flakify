@Test public void should_throw_error_if_sequence_values_is_null(){
  thrown.expectNullPointerException(arrayOfValuesToLookForIsNull());
  strings.assertContainsSequence(someInfo(),"Yoda",null);
}
