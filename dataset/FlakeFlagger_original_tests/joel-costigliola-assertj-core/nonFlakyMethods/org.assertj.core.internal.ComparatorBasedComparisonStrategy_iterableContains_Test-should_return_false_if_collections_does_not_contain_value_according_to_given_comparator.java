@Test public void should_return_false_if_collections_does_not_contain_value_according_to_given_comparator(){
  List<String> hobbits=newArrayList("Merry","Frodo","Merry",null,"Sam");
  assertFalse(caseInsensitiveComparisonStrategy.iterableContains(hobbits,"Pippin"));
  assertFalse(caseInsensitiveComparisonStrategy.iterableContains(hobbits,"SAM "));
  assertFalse(caseInsensitiveComparisonStrategy.iterableContains(hobbits,"Sam "));
}
