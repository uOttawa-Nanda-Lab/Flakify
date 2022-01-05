public void testQueryByCategory(){
  ModelQuery query=repositoryService.createModelQuery().modelCategory("test");
  assertEquals(1,query.list().size());
  assertEquals(1,query.count());
}
