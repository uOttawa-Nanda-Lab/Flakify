@Test public void should_pass_if_objects_are_equal_whatever_custom_comparison_strategy_is(){
  BigDecimal a=new BigDecimal("10.0");
  BigDecimal e=new BigDecimal("10.000");
  assertFalse(a.equals(e));
  comparablesWithCustomComparisonStrategy.assertEqualByComparison(someInfo(),a,e);
}
