@Test public void should_validate_counter_table() throws Exception {
  Map<Class<?>,EntityMeta> metas=ImmutableMap.<Class<?>,EntityMeta>of(UserBean.class,meta);
  Map<String,TableMetadata> tableMetaDatas=ImmutableMap.<String,TableMetadata>of(CQL_COUNTER_TABLE,tableMeta);
  when(schemaContext.fetchTableMetaData()).thenReturn(tableMetaDatas);
  when(meta.getTableName()).thenReturn("UserBean");
  when(schemaContext.entityMetaEntrySet()).thenReturn(metas.entrySet());
  when(schemaContext.hasSimpleCounter()).thenReturn(true);
  bootstrapper.validateOrCreateTables(schemaContext);
  verify(schemaContext).validateAchillesCounter();
}
