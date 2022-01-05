@Test public void should_create_tables() throws Exception {
  Map<Class<?>,EntityMeta> metas=ImmutableMap.<Class<?>,EntityMeta>of(UserBean.class,meta);
  Map<String,TableMetadata> tableMetaDatas=ImmutableMap.<String,TableMetadata>of();
  when(schemaContext.fetchTableMetaData()).thenReturn(tableMetaDatas);
  when(meta.getTableName()).thenReturn("UserBean");
  when(schemaContext.entityMetaEntrySet()).thenReturn(metas.entrySet());
  when(schemaContext.hasSimpleCounter()).thenReturn(false);
  bootstrapper.validateOrCreateTables(schemaContext);
  verify(schemaContext).createTableForEntity(meta);
}
