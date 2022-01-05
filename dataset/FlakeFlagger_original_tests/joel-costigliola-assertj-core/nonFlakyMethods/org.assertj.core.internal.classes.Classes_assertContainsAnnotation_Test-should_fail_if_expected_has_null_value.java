@SuppressWarnings("unchecked") @Test public void should_fail_if_expected_has_null_value(){
  actual=AssertionInfo.class;
  thrown.expectNullPointerException("");
  classes.assertContainsAnnotations(someInfo(),actual,Override.class,null,Deprecated.class);
}
