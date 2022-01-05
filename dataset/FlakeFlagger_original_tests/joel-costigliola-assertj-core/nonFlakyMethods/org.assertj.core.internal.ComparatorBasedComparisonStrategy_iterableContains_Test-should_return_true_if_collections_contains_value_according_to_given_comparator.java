@Test public void should_return_true_if_collections_contains_value_according_to_given_comparator(){
  List<String> hobbits=newArrayList("Merry","Frodo",null,"Merry","Sam");
  assertTrue(caseInsensitiveComparisonStrategy.iterableContains(hobbits,"Sam"));
  assertTrue(caseInsensitiveComparisonStrategy.iterableContains(hobbits,"SAM"));
  assertTrue(caseInsensitiveComparisonStrategy.iterableContains(hobbits,"sAm"));
  assertTrue(caseInsensitiveComparisonStrategy.iterableContains(hobbits,"sam"));
  assertTrue(caseInsensitiveComparisonStrategy.iterableContains(hobbits,null));
}
