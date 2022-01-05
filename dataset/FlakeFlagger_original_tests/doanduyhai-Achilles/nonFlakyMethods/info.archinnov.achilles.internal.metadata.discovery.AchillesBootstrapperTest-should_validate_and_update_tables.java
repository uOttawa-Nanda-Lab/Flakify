@Test public void should_validate_and_update_tables() throws Exception {
  Map<Class<?>,EntityMeta> metas=ImmutableMap.<Class<?>,EntityMeta>of(UserBean.class,meta);
  Map<String,TableMetadata> tableMetaDatas=ImmutableMap.of("userbean",tableMeta);
  when(schemaContext.fetchTableMetaData()).thenReturn(tableMetaDatas);
  when(meta.getTableName()).thenReturn("UserBean");
  when(schemaContext.entityMetaEntrySet()).thenReturn(metas.entrySet());
  when(schemaContext.hasSimpleCounter()).thenReturn(false);
  bootstrapper.validateOrCreateTables(schemaContext);
  verify(schemaContext).validateForEntity(meta,tableMeta);
  verify(schemaContext).updateForEntity(meta,tableMeta);
}
