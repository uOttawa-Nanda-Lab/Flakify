@Test public void should_throw_error_if_offset_is_null_whatever_custom_comparison_strategy_is(){
  thrown.expectNullPointerException(offsetIsNull());
  doublesWithAbsValueComparisonStrategy.assertEqual(someInfo(),new Double(8d),8d,null);
}
