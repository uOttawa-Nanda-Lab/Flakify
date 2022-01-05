public void testQueryByCategoryLike(){
  ModelQuery query=repositoryService.createModelQuery().modelCategoryLike("%te%");
  assertEquals(1,query.list().size());
  assertEquals(1,query.count());
}
