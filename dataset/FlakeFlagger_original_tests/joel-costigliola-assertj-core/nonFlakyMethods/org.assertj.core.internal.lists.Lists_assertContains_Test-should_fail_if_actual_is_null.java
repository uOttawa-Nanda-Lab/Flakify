@Test public void should_fail_if_actual_is_null(){
  thrown.expectAssertionError(actualIsNull());
  lists.assertContains(someInfo(),null,"Yoda",someIndex());
}
