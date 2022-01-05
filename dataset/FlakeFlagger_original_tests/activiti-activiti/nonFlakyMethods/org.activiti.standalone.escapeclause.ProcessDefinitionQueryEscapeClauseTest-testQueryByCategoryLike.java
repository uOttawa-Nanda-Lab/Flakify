public void testQueryByCategoryLike(){
  ProcessDefinitionQuery query=repositoryService.createProcessDefinitionQuery().processDefinitionCategoryLike("%\\_%");
  assertEquals("Examples_",query.singleResult().getCategory());
  assertEquals(1,query.list().size());
  assertEquals(1,query.count());
}
