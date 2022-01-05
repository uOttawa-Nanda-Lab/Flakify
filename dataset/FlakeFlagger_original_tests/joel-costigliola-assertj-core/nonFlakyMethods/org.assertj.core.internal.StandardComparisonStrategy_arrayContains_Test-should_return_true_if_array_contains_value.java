@Test public void should_return_true_if_array_contains_value(){
  String[] hobbits=array("Merry","Frodo",null,"Merry","Sam");
  assertTrue(standardComparisonStrategy.arrayContains(hobbits,"Sam"));
  assertTrue(standardComparisonStrategy.arrayContains(hobbits,"Merry"));
  assertTrue(standardComparisonStrategy.arrayContains(hobbits,null));
}
