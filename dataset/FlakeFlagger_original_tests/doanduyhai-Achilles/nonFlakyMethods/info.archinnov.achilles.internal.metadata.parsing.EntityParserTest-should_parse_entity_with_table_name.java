@Test public void should_parse_entity_with_table_name() throws Exception {
  initEntityParsingContext(BeanWithColumnFamilyName.class);
  EntityMeta meta=parser.parseEntity(entityContext);
  assertThat(meta).isNotNull();
  assertThat(meta.getTableName()).isEqualTo("myOwnCF");
}
