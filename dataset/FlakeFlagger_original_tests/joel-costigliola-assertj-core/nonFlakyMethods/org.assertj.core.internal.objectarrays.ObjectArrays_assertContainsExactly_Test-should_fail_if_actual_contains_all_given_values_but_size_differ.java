@Test public void should_fail_if_actual_contains_all_given_values_but_size_differ(){
  AssertionInfo info=someInfo();
  actual=array("Luke","Leia","Luke");
  Object[] expected={"Luke","Leia"};
  try {
    arrays.assertContainsExactly(info,actual,expected);
  }
 catch (  AssertionError e) {
    assertThat(e).hasMessage(shouldHaveSameSizeAs(actual,actual.length,expected.length).create(null,info.representation()));
    return;
  }
  failBecauseExpectedAssertionErrorWasNotThrown();
}
