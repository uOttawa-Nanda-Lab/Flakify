@Test public void should_keep_description_set_by_user(){
  try {
    assertion.as("user description").failWithMessage("fail %d %s",5,"times");
  }
 catch (  AssertionError e) {
    assertThat(e).hasMessage("[user description] fail 5 times");
    return;
  }
  failBecauseExpectedAssertionErrorWasNotThrown();
}
