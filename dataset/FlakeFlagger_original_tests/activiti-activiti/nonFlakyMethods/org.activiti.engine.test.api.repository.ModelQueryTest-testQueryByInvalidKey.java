public void testQueryByInvalidKey(){
  ModelQuery query=repositoryService.createModelQuery().modelKey("invalid");
  assertNull(query.singleResult());
  assertEquals(0,query.list().size());
  assertEquals(0,query.count());
}
