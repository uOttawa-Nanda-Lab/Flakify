@Test public void should_fail_if_first_float_is_null_but_not_the_second_whatever_custom_comparison_strategy_is(){
  thrown.expectAssertionError(actualIsNull());
  floatsWithAbsValueComparisonStrategy.assertEqual(someInfo(),null,8f,offset(1f));
}
