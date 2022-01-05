@Test public void should_fail_if_actual_is_less_than_other_according_to_custom_comparison_strategy(){
  AssertionInfo info=someInfo();
  try {
    bigDecimalsWithAbsValueComparisonStrategy.assertGreaterThanOrEqualTo(info,ONE,TEN.negate());
  }
 catch (  AssertionError e) {
    verify(failures).failure(info,shouldBeGreaterOrEqual(ONE,TEN.negate(),absValueComparisonStrategy));
    return;
  }
  failBecauseExpectedAssertionErrorWasNotThrown();
}
