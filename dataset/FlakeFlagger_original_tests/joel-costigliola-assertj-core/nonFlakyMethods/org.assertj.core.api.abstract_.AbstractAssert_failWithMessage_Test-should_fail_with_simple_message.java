@Test public void should_fail_with_simple_message(){
  try {
    assertion.failWithMessage("fail");
  }
 catch (  AssertionError e) {
    assertThat(e).hasMessage("fail");
    return;
  }
  failBecauseExpectedAssertionErrorWasNotThrown();
}
