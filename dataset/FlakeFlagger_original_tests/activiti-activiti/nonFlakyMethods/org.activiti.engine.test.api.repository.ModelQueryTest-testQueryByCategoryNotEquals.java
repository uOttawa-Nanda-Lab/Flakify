public void testQueryByCategoryNotEquals(){
  ModelQuery query=repositoryService.createModelQuery().modelCategoryNotEquals("aap");
  assertEquals(1,query.list().size());
  assertEquals(1,query.count());
}
