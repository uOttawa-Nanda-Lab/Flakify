@Test public void should_update_table_for_entity() throws Exception {
  EntityMeta entityMeta=mock(EntityMeta.class);
  TableMetadata tableMetadata=mock(TableMetadata.class);
  context.updateForEntity(entityMeta,tableMetadata);
  verify(tableUpdater).updateTableForEntity(session,entityMeta,tableMetadata);
}
