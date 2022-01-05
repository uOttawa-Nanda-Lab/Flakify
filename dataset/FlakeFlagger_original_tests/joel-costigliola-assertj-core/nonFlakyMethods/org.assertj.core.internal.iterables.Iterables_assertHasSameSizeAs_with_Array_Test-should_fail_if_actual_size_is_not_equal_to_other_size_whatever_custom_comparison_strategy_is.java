@Test public void should_fail_if_actual_size_is_not_equal_to_other_size_whatever_custom_comparison_strategy_is(){
  AssertionInfo info=someInfo();
  Collection<String> actual=newArrayList("Yoda");
  String[] other=array("Solo","Luke","Leia");
  try {
    iterablesWithCaseInsensitiveComparisonStrategy.assertHasSameSizeAs(info,actual,other);
  }
 catch (  AssertionError e) {
    assertThat(e).hasMessage(shouldHaveSameSizeAs(actual,actual.size(),other.length).create(null,info.representation()));
    return;
  }
  failBecauseExpectedAssertionErrorWasNotThrown();
}
