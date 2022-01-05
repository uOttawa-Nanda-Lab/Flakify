@Test public void should_return_false_if_collections_is_empty_whatever_given_comparator_is(){
  assertFalse(caseInsensitiveComparisonStrategy.iterableContains(newArrayList(),"anyone"));
}
