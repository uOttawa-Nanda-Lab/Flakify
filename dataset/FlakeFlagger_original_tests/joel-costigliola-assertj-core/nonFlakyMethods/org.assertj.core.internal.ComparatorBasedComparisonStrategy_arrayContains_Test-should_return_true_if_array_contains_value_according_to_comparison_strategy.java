@Test public void should_return_true_if_array_contains_value_according_to_comparison_strategy(){
  String[] hobbits=array("Merry","Frodo","Merry","Sam");
  assertTrue(caseInsensitiveComparisonStrategy.arrayContains(hobbits,"Sam"));
  assertTrue(caseInsensitiveComparisonStrategy.arrayContains(hobbits,"SAM"));
  assertTrue(caseInsensitiveComparisonStrategy.arrayContains(hobbits,"Merry"));
  assertTrue(caseInsensitiveComparisonStrategy.arrayContains(hobbits,"MerRy"));
}
