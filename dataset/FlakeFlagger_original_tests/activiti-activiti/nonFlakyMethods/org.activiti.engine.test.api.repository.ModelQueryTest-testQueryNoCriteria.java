public void testQueryNoCriteria(){
  ModelQuery query=repositoryService.createModelQuery();
  assertEquals(1,query.list().size());
  assertEquals(1,query.count());
}
