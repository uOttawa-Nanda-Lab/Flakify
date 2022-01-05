@Test public void should_fail_since_actual_is_not_negative_according_to_custom_comparison_strategy(){
  thrown.expectAssertionError("Expecting:\n <1L>\nto be less than:\n <0L>");
  longsWithAbsValueComparisonStrategy.assertIsNegative(someInfo(),1L);
}
