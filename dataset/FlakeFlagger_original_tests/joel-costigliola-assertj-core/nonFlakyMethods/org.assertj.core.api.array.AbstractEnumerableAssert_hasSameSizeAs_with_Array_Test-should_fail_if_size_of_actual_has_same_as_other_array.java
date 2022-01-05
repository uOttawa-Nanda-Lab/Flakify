@Test public void should_fail_if_size_of_actual_has_same_as_other_array(){
  final byte[] actual=new byte[]{1,2};
  final byte[] other=new byte[]{1,2,3};
  try {
    assertThat(actual).hasSameSizeAs(other);
  }
 catch (  AssertionError e) {
    assertThat(e).hasMessage(shouldHaveSameSizeAs(actual,actual.length,other.length).create());
    return;
  }
  failBecauseExpectedAssertionErrorWasNotThrown();
}
