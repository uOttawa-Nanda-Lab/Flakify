@Test public void should_fail_if_actual_is_null(){
  thrown.expectAssertionError(actualIsNull());
  arrays.assertHasSameSizeAs(someInfo(),null,newArrayList("Solo","Leia"));
}
