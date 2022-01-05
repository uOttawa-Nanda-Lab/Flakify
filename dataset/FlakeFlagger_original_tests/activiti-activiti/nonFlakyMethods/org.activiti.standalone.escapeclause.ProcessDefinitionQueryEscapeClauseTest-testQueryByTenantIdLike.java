public void testQueryByTenantIdLike(){
  ProcessDefinitionQuery query=repositoryService.createProcessDefinitionQuery().processDefinitionTenantIdLike("%\\%%");
  assertEquals("One%",query.singleResult().getTenantId());
  assertEquals(1,query.list().size());
  assertEquals(1,query.count());
  query=repositoryService.createProcessDefinitionQuery().processDefinitionTenantIdLike("%\\_%");
  assertEquals("Two_",query.singleResult().getTenantId());
  assertEquals(1,query.list().size());
  assertEquals(1,query.count());
  query=repositoryService.createProcessDefinitionQuery().latestVersion().processDefinitionTenantIdLike("%\\%%");
  assertEquals("One%",query.singleResult().getTenantId());
  assertEquals(1,query.list().size());
  assertEquals(1,query.count());
  query=repositoryService.createProcessDefinitionQuery().latestVersion().processDefinitionTenantIdLike("%\\_%");
  assertEquals("Two_",query.singleResult().getTenantId());
  assertEquals(1,query.list().size());
  assertEquals(1,query.count());
}
