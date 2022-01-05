public void testQueryByKeyLike(){
  ProcessDefinitionQuery query=repositoryService.createProcessDefinitionQuery().processDefinitionKeyLike("%\\_%");
  assertEquals("two_",query.singleResult().getKey());
  assertEquals(1,query.list().size());
  assertEquals(1,query.count());
}
