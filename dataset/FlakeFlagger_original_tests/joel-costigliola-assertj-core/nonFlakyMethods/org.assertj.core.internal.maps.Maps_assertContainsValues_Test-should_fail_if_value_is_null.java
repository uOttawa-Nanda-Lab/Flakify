@Test public void should_fail_if_value_is_null(){
  thrown.expectNullPointerException("The array of values to look for should not be null");
  maps.assertContainsValues(someInfo(),actual,(String[])null);
}
