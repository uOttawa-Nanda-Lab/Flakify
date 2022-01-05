@Test public void should_parse_entity_with_scheme_update_enabled() throws Exception {
  initEntityParsingContext(BeanWithClusteredId.class);
  configContext.setEnableSchemaUpdate(false);
  configContext.setEnableSchemaUpdateForTables(ImmutableMap.of("BeanWithClusteredId",true));
  EntityMeta meta=parser.parseEntity(entityContext);
  assertThat(meta.isSchemaUpdateEnabled()).isTrue();
}
