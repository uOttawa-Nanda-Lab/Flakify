@Test public void should_validate_for_entity() throws Exception {
  EntityMeta entityMeta=mock(EntityMeta.class);
  TableMetadata tableMetaData=mock(TableMetadata.class);
  context.validateForEntity(entityMeta,tableMetaData);
  verify(tableValidator).validateForEntity(entityMeta,tableMetaData);
}
