@Test public void should_fail_if_floats_are_not_equal_within_offset_whatever_custom_comparison_strategy_is(){
  AssertionInfo info=someInfo();
  Offset<Float> offset=offset(1f);
  try {
    floatsWithAbsValueComparisonStrategy.assertEqual(info,new Float(6f),8f,offset);
  }
 catch (  AssertionError e) {
    verify(failures).failure(info,shouldBeEqual(6f,8f,offset,2f));
    return;
  }
  failBecauseExpectedAssertionErrorWasNotThrown();
}
