@Test public void should_create_table_for_entity() throws Exception {
  EntityMeta entityMeta=mock(EntityMeta.class);
  context.createTableForEntity(entityMeta);
  verify(tableCreator).createTableForEntity(session,entityMeta,true);
}
