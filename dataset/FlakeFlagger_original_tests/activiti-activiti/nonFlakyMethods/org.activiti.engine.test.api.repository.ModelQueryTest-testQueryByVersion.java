public void testQueryByVersion(){
  ModelQuery query=repositoryService.createModelQuery().modelVersion(1);
  assertEquals(1,query.list().size());
  assertEquals(1,query.count());
}
