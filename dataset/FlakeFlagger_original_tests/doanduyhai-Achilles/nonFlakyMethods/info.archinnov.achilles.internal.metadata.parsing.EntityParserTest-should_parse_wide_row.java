@Test public void should_parse_wide_row() throws Exception {
  initEntityParsingContext(ClusteredEntity.class);
  EntityMeta meta=parser.parseEntity(entityContext);
  assertThat(meta.isClusteredEntity()).isTrue();
  assertThat(meta.getIdMeta().getPropertyName()).isEqualTo("id");
  assertThat(meta.getIdMeta().<EmbeddedKey>getValueClass()).isEqualTo(EmbeddedKey.class);
  assertThat(meta.getPropertyMetas()).hasSize(2);
  assertThat(meta.getPropertyMetas().get("id").type()).isEqualTo(EMBEDDED_ID);
  assertThat(meta.getPropertyMetas().get("value").type()).isEqualTo(SIMPLE);
}
