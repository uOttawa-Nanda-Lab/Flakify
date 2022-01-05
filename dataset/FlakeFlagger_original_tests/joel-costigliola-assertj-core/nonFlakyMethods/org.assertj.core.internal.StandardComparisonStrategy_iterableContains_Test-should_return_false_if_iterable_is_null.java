@Test public void should_return_false_if_iterable_is_null(){
  assertFalse(standardComparisonStrategy.iterableContains(null,"Sauron"));
}
