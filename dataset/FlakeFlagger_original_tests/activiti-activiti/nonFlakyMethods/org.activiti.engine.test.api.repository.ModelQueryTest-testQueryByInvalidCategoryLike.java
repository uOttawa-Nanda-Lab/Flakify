public void testQueryByInvalidCategoryLike(){
  ModelQuery query=repositoryService.createModelQuery().modelCategoryLike("%invalid%");
  assertNull(query.singleResult());
  assertEquals(0,query.list().size());
  assertEquals(0,query.count());
}
