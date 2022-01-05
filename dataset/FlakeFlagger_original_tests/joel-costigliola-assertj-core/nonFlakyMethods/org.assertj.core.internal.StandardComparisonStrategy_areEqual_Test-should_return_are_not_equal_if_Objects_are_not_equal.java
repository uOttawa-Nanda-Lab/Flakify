@Test public void should_return_are_not_equal_if_Objects_are_not_equal(){
  assertFalse(standardComparisonStrategy.areEqual("Yoda",2));
}
