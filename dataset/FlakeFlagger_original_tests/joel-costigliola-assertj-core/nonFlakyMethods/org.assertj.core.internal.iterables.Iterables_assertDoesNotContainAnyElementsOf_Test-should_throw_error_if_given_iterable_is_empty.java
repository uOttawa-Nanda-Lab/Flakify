@Test public void should_throw_error_if_given_iterable_is_empty(){
  thrown.expectIllegalArgumentException(iterableValuesToLookForIsEmpty());
  iterables.assertDoesNotContainAnyElementsOf(someInfo(),actual,Collections.<String>emptyList());
}
