public void testQueryByInvalidName(){
  ModelQuery query=repositoryService.createModelQuery().modelName("invalid");
  assertNull(query.singleResult());
  assertEquals(0,query.list().size());
  assertEquals(0,query.count());
}
