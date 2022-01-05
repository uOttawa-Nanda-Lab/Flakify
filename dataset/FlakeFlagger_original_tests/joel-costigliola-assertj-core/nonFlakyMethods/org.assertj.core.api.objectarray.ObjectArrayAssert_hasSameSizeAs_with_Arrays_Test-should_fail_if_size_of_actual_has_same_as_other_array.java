@Test public void should_fail_if_size_of_actual_has_same_as_other_array(){
  final String[] actual=array("Luke","Yoda");
  final String[] other=array("Yoda");
  try {
    assertThat(actual).hasSameSizeAs(other);
  }
 catch (  AssertionError e) {
    assertThat(e).hasMessage(shouldHaveSameSizeAs(actual,actual.length,other.length).create());
    return;
  }
  failBecauseExpectedAssertionErrorWasNotThrown();
}
