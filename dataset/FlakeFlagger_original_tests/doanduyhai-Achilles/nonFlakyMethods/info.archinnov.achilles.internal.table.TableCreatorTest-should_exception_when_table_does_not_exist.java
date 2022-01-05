@Test public void should_exception_when_table_does_not_exist() throws Exception {
  meta=new EntityMeta();
  meta.setTableName("tableName");
  meta.setClassName("entityName");
  exception.expect(AchillesInvalidTableException.class);
  exception.expectMessage("The required table 'tablename' does not exist for entity 'entityName'");
  creator.createTableForEntity(session,meta,false);
}
