public void testQueryByResourceNameLike(){
  ProcessDefinitionQuery query=repositoryService.createProcessDefinitionQuery().processDefinitionResourceNameLike("%\\%%");
  assertEquals("org/activiti/engine/test/repository/one%.bpmn20.xml",query.singleResult().getResourceName());
  assertEquals(1,query.list().size());
  assertEquals(1,query.count());
  query=repositoryService.createProcessDefinitionQuery().processDefinitionResourceNameLike("%\\_%");
  assertEquals("org/activiti/engine/test/repository/two_.bpmn20.xml",query.singleResult().getResourceName());
  assertEquals(1,query.list().size());
  assertEquals(1,query.count());
}
