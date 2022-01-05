@Test public void should_fail_if_size_of_actual_is_not_equal_to_expected_size(){
  AssertionInfo info=someInfo();
  List<String> other=newArrayList("Solo","Leia","Yoda");
  try {
    arrays.assertHasSameSizeAs(info,actual,other);
  }
 catch (  AssertionError e) {
    assertThat(e).hasMessage(shouldHaveSameSizeAs(actual,actual.length,other.size()).create(null,info.representation()));
    return;
  }
  failBecauseExpectedAssertionErrorWasNotThrown();
}
