@Test public void should_fail_if_actual_is_null(){
  thrown.expectAssertionError(actualIsNull());
  bigDecimals.assertIsCloseTo(someInfo(),null,ONE,offset(ONE));
}
