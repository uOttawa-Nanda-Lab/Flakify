@Test public void should_throw_error_if_sequence_values_is_empty(){
  thrown.expectIllegalArgumentException(arrayOfValuesToLookForIsEmpty());
  strings.assertContainsSequence(someInfo(),"Yoda",new String[0]);
}
