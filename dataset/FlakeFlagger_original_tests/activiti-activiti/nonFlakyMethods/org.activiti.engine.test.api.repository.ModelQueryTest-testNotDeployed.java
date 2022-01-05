public void testNotDeployed(){
  ModelQuery query=repositoryService.createModelQuery().notDeployed();
  assertEquals(1,query.count());
  assertEquals(1,query.list().size());
}
