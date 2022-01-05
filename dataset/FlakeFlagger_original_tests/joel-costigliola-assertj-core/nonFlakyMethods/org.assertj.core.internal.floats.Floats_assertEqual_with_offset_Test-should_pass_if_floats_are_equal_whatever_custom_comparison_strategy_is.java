@Test public void should_pass_if_floats_are_equal_whatever_custom_comparison_strategy_is(){
  floatsWithAbsValueComparisonStrategy.assertEqual(someInfo(),new Float(8f),new Float(8f),offset(1f));
}
