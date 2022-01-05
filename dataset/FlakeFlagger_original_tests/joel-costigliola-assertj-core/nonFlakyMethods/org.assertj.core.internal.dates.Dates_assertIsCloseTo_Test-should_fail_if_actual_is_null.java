@Test public void should_fail_if_actual_is_null(){
  thrown.expectAssertionError(actualIsNull());
  dates.assertIsCloseTo(someInfo(),null,parseDate("2010-01-01"),10);
}
