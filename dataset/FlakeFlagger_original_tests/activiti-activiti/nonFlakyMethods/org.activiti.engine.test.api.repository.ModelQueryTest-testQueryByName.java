public void testQueryByName() throws Exception {
  ModelQuery query=repositoryService.createModelQuery().modelName("my model");
  Model model=query.singleResult();
  assertNotNull(model);
  assertEquals("bytes",new String(repositoryService.getModelEditorSource(model.getId()),"utf-8"));
  assertEquals(1,query.list().size());
  assertEquals(1,query.count());
}
