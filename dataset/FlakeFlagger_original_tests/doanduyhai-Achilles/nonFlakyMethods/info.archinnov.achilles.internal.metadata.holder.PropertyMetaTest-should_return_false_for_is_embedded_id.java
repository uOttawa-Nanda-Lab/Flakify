@Test public void should_return_false_for_is_embedded_id() throws Exception {
  PropertyMeta idMeta=PropertyMetaTestBuilder.valueClass(EmbeddedKey.class).type(ID).build();
  assertThat(idMeta.isEmbeddedId()).isFalse();
}
