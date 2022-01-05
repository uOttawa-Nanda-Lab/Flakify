@Test public void should_fail_if_size_of_actual_is_not_equal_to_size_of_other(){
  AssertionInfo info=someInfo();
  Map<?,?> other=mapOf(entry("name","Solo"));
  try {
    maps.assertHasSameSizeAs(info,actual,other);
    failBecauseExpectedAssertionErrorWasNotThrown();
  }
 catch (  AssertionError e) {
    assertThat(e).hasMessage(shouldHaveSameSizeAs(actual,actual.size(),other.size()).create(null,info.representation()));
  }
}
