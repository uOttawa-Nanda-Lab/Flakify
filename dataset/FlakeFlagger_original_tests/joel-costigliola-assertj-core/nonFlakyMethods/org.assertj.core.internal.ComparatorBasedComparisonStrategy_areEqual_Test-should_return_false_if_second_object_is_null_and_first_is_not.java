@Test public void should_return_false_if_second_object_is_null_and_first_is_not(){
  assertFalse(caseInsensitiveComparisonStrategy.areEqual("Yoda",null));
}
