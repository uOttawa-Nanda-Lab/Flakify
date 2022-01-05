public void testNativeQuery(){
  assertEquals("ACT_RE_DEPLOYMENT",managementService.getTableName(Deployment.class));
  assertEquals("ACT_RE_DEPLOYMENT",managementService.getTableName(DeploymentEntity.class));
  String tableName=managementService.getTableName(Deployment.class);
  String baseQuerySql="SELECT * FROM " + tableName;
  assertEquals(2,repositoryService.createNativeDeploymentQuery().sql(baseQuerySql).list().size());
  assertEquals(1,repositoryService.createNativeDeploymentQuery().sql(baseQuerySql + " where NAME_ = #{name}").parameter("name","org/activiti/engine/test/repository/one.bpmn20.xml").list().size());
  assertEquals(2,repositoryService.createNativeDeploymentQuery().sql(baseQuerySql).listPage(0,2).size());
  assertEquals(1,repositoryService.createNativeDeploymentQuery().sql(baseQuerySql).listPage(1,3).size());
}
