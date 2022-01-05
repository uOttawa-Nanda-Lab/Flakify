@Test public void fail_with_good_message_when_assertion_is_failing(){
  try {
    assertThatThrownBy(raisingException("boom")).hasMessage("yo");
  }
 catch (  AssertionError ae) {
    assertThat(ae).hasMessageContaining("Expecting message:").hasMessageContaining("<\"yo\">").hasMessageContaining("but was:").hasMessageContaining("<\"boom\">");
    return;
  }
  shouldHaveThrown(AssertionError.class);
}
