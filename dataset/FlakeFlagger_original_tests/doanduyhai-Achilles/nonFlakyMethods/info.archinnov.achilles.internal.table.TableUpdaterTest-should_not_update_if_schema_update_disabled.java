@Test public void should_not_update_if_schema_update_disabled() throws Exception {
  meta=new EntityMeta();
  meta.setSchemaUpdateEnabled(false);
  updater.updateTableForEntity(session,meta,tableMeta);
  verifyZeroInteractions(session);
}
