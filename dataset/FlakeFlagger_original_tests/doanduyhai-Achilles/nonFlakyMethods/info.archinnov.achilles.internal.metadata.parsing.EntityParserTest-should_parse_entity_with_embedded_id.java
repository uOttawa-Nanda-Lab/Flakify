@Test public void should_parse_entity_with_embedded_id() throws Exception {
  initEntityParsingContext(BeanWithClusteredId.class);
  EntityMeta meta=parser.parseEntity(entityContext);
  assertThat(meta).isNotNull();
  assertThat(meta.<EmbeddedKey>getIdClass()).isEqualTo(EmbeddedKey.class);
  PropertyMeta idMeta=meta.getIdMeta();
  assertThat(idMeta.isEmbeddedId()).isTrue();
  assertThat(idMeta.getComponentClasses()).containsExactly(Long.class,String.class);
}
