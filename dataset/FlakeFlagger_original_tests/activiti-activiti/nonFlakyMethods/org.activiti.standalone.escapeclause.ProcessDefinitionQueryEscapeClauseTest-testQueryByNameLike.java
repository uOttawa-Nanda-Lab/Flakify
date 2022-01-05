public void testQueryByNameLike(){
  ProcessDefinitionQuery query=repositoryService.createProcessDefinitionQuery().processDefinitionNameLike("%\\%%");
  assertEquals("One%",query.singleResult().getName());
  assertEquals(1,query.list().size());
  assertEquals(1,query.count());
  query=repositoryService.createProcessDefinitionQuery().processDefinitionNameLike("%\\_%");
  assertEquals("Two_",query.singleResult().getName());
  assertEquals(1,query.list().size());
  assertEquals(1,query.count());
}
