@Test public void should_return_false_if_array_is_empty(){
  assertFalse(standardComparisonStrategy.arrayContains(new String[]{},"Pippin"));
}
