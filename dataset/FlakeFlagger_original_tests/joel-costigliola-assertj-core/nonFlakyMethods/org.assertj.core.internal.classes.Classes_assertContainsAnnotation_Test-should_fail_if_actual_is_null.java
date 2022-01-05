@SuppressWarnings("unchecked") @Test public void should_fail_if_actual_is_null(){
  actual=null;
  thrown.expectAssertionError(actualIsNull());
  classes.assertContainsAnnotations(someInfo(),actual,Override.class);
}
