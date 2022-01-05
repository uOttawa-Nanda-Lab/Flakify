@Test public void should_return_false_if_first_object_is_null_and_second_is_not(){
  assertFalse(caseInsensitiveComparisonStrategy.areEqual(null,"Yoda"));
}
