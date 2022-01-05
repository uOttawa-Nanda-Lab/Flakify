@Test public void should_fail_with_message_having_args(){
  try {
    assertion.failWithMessage("fail %d %s",5,"times");
  }
 catch (  AssertionError e) {
    assertThat(e).hasMessage("fail 5 times");
    return;
  }
  failBecauseExpectedAssertionErrorWasNotThrown();
}
