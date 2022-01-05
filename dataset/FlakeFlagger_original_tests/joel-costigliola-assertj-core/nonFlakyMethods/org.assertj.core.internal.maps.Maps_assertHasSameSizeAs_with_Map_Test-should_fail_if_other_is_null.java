@Test public void should_fail_if_other_is_null(){
  thrown.expectNullPointerException("The Map to compare actual size with should not be null");
  maps.assertHasSameSizeAs(someInfo(),actual,(Map<?,?>)null);
}
