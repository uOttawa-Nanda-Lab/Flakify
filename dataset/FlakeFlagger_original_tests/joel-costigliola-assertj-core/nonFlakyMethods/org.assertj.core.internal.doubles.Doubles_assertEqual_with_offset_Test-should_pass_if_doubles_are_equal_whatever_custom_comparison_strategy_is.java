@Test public void should_pass_if_doubles_are_equal_whatever_custom_comparison_strategy_is(){
  doublesWithAbsValueComparisonStrategy.assertEqual(someInfo(),new Double(8d),new Double(8d),offset(1d));
}
