@Test public void should_collect_all_errors_when_using_flat_extracting() throws Exception {
  List<CartoonCharacter> characters=asList(homer,fred);
  softly.assertThat(characters).flatExtracting(children()).overridingErrorMessage("error 1").hasSize(0).overridingErrorMessage("error 2").isEmpty();
  try {
    softly.assertAll();
    shouldHaveThrown(SoftAssertionError.class);
  }
 catch (  SoftAssertionError e) {
    assertThat(e.getErrors()).containsExactly("error 1","error 2");
  }
}
