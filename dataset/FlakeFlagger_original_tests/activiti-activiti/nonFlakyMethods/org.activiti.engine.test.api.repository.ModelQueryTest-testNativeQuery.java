public void testNativeQuery(){
  assertEquals("ACT_RE_MODEL",managementService.getTableName(Model.class));
  assertEquals("ACT_RE_MODEL",managementService.getTableName(ModelEntity.class));
  String tableName=managementService.getTableName(Model.class);
  String baseQuerySql="SELECT * FROM " + tableName;
  assertEquals(1,repositoryService.createNativeModelQuery().sql(baseQuerySql).list().size());
  assertEquals(1,repositoryService.createNativeProcessDefinitionQuery().sql(baseQuerySql + " where NAME_ = #{name}").parameter("name","my model").list().size());
  assertEquals(1,repositoryService.createNativeProcessDefinitionQuery().sql(baseQuerySql).listPage(0,1).size());
  assertEquals(0,repositoryService.createNativeProcessDefinitionQuery().sql(baseQuerySql).listPage(1,5).size());
}
