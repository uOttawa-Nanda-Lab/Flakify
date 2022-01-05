@Test public void should_throw_error_if_Index_is_null_whatever_custom_comparison_strategy_is(){
  thrown.expectNullPointerException("Index should not be null");
  arraysWithCustomComparisonStrategy.assertDoesNotContain(someInfo(),actual,"YOda",null);
}
