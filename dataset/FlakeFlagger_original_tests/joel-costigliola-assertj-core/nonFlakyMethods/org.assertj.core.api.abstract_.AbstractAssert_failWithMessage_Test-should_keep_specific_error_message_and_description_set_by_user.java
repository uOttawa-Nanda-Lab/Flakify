@Test public void should_keep_specific_error_message_and_description_set_by_user(){
  try {
    assertion.as("test context").overridingErrorMessage("my %d errors %s",5,"!").failWithMessage("%d %s",5,"time");
  }
 catch (  AssertionError e) {
    assertThat(e).hasMessage("[test context] my 5 errors !");
    return;
  }
  failBecauseExpectedAssertionErrorWasNotThrown();
}
