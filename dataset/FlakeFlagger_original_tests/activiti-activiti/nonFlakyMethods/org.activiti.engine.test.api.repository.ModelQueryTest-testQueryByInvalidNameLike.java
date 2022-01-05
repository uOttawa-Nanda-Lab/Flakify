public void testQueryByInvalidNameLike(){
  ModelQuery query=repositoryService.createModelQuery().modelNameLike("%invalid%");
  assertNull(query.singleResult());
  assertEquals(0,query.list().size());
  assertEquals(0,query.count());
}
