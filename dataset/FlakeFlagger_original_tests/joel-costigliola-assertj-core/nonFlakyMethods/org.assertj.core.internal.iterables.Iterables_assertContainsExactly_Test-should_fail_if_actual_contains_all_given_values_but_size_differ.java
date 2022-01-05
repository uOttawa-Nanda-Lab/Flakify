@Test public void should_fail_if_actual_contains_all_given_values_but_size_differ(){
  AssertionInfo info=someInfo();
  actual=newArrayList("Luke","Leia","Luke");
  Object[] expected={"Luke","Leia"};
  try {
    iterables.assertContainsExactly(info,actual,expected);
  }
 catch (  AssertionError e) {
    assertThat(e).hasMessage(shouldHaveSameSizeAs(actual,actual.size(),expected.length).create(null,info.representation()));
    return;
  }
  failBecauseExpectedAssertionErrorWasNotThrown();
}
