@Test public void should_pass_if_doubles_are_equal_within_offset_whatever_custom_comparison_strategy_is(){
  doublesWithAbsValueComparisonStrategy.assertEqual(someInfo(),new Double(6d),8d,offset(2d));
}
