@Test public void should_not_remove_value_from_collections_since_it_does_not_match_any_collections_elements_according_to_given_comparator(){
  List<String> hobbits=newArrayList("Merry","Frodo",null,"Merry","Sam");
  assertTrue(caseInsensitiveComparisonStrategy.iterableContains(hobbits,"SAM"));
  caseInsensitiveComparisonStrategy.iterableRemoves(hobbits,"SAM ");
  assertTrue(caseInsensitiveComparisonStrategy.iterableContains(hobbits,"SAM"));
}
