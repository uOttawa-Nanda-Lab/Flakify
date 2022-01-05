@Test public void should_fail_if_actual_is_empty(){
  thrown.expectAssertionError(actualIsEmpty());
  lists.assertContains(someInfo(),emptyList(),"Yoda",someIndex());
}
