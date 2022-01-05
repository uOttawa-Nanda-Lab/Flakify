public void testQueryByInvalidCategory(){
  ModelQuery query=repositoryService.createModelQuery().modelCategory("invalid");
  assertNull(query.singleResult());
  assertEquals(0,query.list().size());
  assertEquals(0,query.count());
}
