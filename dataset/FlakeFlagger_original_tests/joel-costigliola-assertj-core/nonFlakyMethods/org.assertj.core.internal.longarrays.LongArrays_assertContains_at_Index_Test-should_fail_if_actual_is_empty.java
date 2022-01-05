@Test public void should_fail_if_actual_is_empty(){
  thrown.expectAssertionError(actualIsEmpty());
  arrays.assertContains(someInfo(),emptyArray(),8L,someIndex());
}
