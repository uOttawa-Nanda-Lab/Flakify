@Test public void should_pass_if_floats_are_equal_within_offset_whatever_custom_comparison_strategy_is(){
  floatsWithAbsValueComparisonStrategy.assertEqual(someInfo(),new Float(6f),8f,offset(2f));
}
