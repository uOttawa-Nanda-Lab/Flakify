@Test public void should_have_empty_text_as_value(){
  assertThat(EmptyTextDescription.emptyText().value()).isEmpty();
}
