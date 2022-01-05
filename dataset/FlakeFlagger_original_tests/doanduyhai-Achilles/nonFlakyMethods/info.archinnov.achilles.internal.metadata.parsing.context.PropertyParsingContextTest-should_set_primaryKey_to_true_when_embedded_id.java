@Test public void should_set_primaryKey_to_true_when_embedded_id() throws Exception {
  PropertyParsingContext context=new PropertyParsingContext(null,null);
  context.setEmbeddedId(true);
  assertThat(context.isEmbeddedId()).isTrue();
  assertThat(context.isPrimaryKey()).isTrue();
}
