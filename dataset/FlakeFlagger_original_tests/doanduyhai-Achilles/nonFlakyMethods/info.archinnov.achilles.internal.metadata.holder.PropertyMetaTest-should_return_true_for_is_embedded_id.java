@Test public void should_return_true_for_is_embedded_id() throws Exception {
  PropertyMeta idMeta=PropertyMetaTestBuilder.valueClass(EmbeddedKey.class).type(EMBEDDED_ID).build();
  assertThat(idMeta.isEmbeddedId()).isTrue();
}
