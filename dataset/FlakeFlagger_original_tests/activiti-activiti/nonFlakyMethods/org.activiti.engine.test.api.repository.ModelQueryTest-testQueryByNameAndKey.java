public void testQueryByNameAndKey(){
  ModelQuery query=repositoryService.createModelQuery().modelKey("someKey");
  Model model=query.singleResult();
  assertNotNull(model);
  assertEquals(1,query.list().size());
  assertEquals(1,query.count());
}
