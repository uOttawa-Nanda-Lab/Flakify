@Test public void should_remove_value_from_collections_since_it_matches_one_collections_element_according_to_given_comparator(){
  List<String> hobbits=newArrayList("Merry","Frodo",null,"Merry","Sam");
  assertTrue(caseInsensitiveComparisonStrategy.iterableContains(hobbits,"SAM"));
  caseInsensitiveComparisonStrategy.iterableRemoves(hobbits,"Sam");
  assertFalse(caseInsensitiveComparisonStrategy.iterableContains(hobbits,"SAM"));
  caseInsensitiveComparisonStrategy.iterableRemoves(hobbits,null);
  assertFalse(caseInsensitiveComparisonStrategy.iterableContains(hobbits,null));
}
