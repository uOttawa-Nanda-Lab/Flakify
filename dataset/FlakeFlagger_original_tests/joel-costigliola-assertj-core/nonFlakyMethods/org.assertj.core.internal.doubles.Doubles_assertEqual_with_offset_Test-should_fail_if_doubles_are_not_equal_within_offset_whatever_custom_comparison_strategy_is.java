@Test public void should_fail_if_doubles_are_not_equal_within_offset_whatever_custom_comparison_strategy_is(){
  AssertionInfo info=someInfo();
  Offset<Double> offset=offset(1d);
  try {
    doublesWithAbsValueComparisonStrategy.assertEqual(info,new Double(6d),new Double(8d),offset);
  }
 catch (  AssertionError e) {
    verify(failures).failure(info,shouldBeEqual(6d,8d,offset,2d));
    return;
  }
  failBecauseExpectedAssertionErrorWasNotThrown();
}
