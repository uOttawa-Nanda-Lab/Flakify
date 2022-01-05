public void testQueryByKey(){
  ModelQuery query=repositoryService.createModelQuery().modelName("my model").modelKey("someKey");
  Model model=query.singleResult();
  assertNotNull(model);
  assertEquals(1,query.list().size());
  assertEquals(1,query.count());
}
