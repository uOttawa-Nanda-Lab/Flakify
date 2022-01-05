public void testNativeQuery(){
  assertEquals("ACT_RE_PROCDEF",managementService.getTableName(ProcessDefinition.class));
  assertEquals("ACT_RE_PROCDEF",managementService.getTableName(ProcessDefinitionEntity.class));
  String tableName=managementService.getTableName(ProcessDefinition.class);
  String baseQuerySql="SELECT * FROM " + tableName;
  assertEquals(3,repositoryService.createNativeProcessDefinitionQuery().sql(baseQuerySql).list().size());
  assertEquals(3,repositoryService.createNativeProcessDefinitionQuery().sql(baseQuerySql + " where KEY_ like #{key}").parameter("key","%o%").list().size());
  assertEquals(2,repositoryService.createNativeProcessDefinitionQuery().sql(baseQuerySql + " where NAME_ = #{name}").parameter("name","One").list().size());
  assertEquals(2,repositoryService.createNativeProcessDefinitionQuery().sql(baseQuerySql).listPage(0,2).size());
  assertEquals(2,repositoryService.createNativeProcessDefinitionQuery().sql(baseQuerySql).listPage(1,3).size());
}
