@Test public void should_fail_if_no_throwable_was_thrown(){
  try {
    assertThatThrownBy(notRaisingException()).hasMessage("yo");
  }
 catch (  AssertionError e) {
    assertThat(e).hasMessage("Expecting code to raise a throwable.");
    return;
  }
  shouldHaveThrown(AssertionError.class);
}
