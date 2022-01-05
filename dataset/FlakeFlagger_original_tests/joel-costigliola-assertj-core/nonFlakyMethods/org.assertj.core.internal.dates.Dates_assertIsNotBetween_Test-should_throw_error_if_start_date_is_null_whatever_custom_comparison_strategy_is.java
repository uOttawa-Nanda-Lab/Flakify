@Test public void should_throw_error_if_start_date_is_null_whatever_custom_comparison_strategy_is(){
  thrown.expectNullPointerException(startDateToCompareActualWithIsNull());
  Date end=parseDate("2011-09-30");
  datesWithCustomComparisonStrategy.assertIsNotBetween(someInfo(),actual,null,end,true,true);
}
