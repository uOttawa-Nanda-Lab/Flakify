@Test public void should_fail_if_actual_is_null(){
  thrown.expectAssertionError(actualIsNull());
  longs.assertIsCloseTo(someInfo(),null,ONE,within(ONE));
}
