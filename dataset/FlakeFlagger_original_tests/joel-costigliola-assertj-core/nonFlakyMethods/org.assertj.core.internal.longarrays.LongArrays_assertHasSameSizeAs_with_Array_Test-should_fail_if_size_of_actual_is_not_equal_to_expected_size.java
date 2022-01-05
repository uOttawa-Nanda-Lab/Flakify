@Test public void should_fail_if_size_of_actual_is_not_equal_to_expected_size(){
  AssertionInfo info=someInfo();
  String[] other=array("Solo","Leia","Yoda","Luke");
  try {
    arrays.assertHasSameSizeAs(info,actual,other);
  }
 catch (  AssertionError e) {
    assertThat(e).hasMessage(shouldHaveSameSizeAs(actual,actual.length,other.length).create(null,info.representation()));
    return;
  }
  failBecauseExpectedAssertionErrorWasNotThrown();
}
