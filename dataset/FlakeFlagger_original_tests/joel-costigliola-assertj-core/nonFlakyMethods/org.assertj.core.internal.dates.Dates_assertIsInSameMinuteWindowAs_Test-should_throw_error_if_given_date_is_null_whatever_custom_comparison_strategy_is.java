@Test public void should_throw_error_if_given_date_is_null_whatever_custom_comparison_strategy_is(){
  thrown.expectNullPointerException(dateToCompareActualWithIsNull());
  datesWithCustomComparisonStrategy.assertIsInSameMinuteWindowAs(someInfo(),actual,null);
}
