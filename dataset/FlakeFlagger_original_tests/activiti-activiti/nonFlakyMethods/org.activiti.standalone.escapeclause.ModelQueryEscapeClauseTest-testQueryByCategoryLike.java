public void testQueryByCategoryLike() throws Exception {
  ModelQuery query=repositoryService.createModelQuery().modelCategoryLike("%\\%%");
  Model model=query.singleResult();
  assertNotNull(model);
  assertEquals("someKey1",model.getKey());
  assertEquals("bytes",new String(repositoryService.getModelEditorSource(model.getId()),"utf-8"));
  assertEquals(1,query.list().size());
  assertEquals(1,query.count());
  query=repositoryService.createModelQuery().modelCategoryLike("%\\_%");
  model=query.singleResult();
  assertNotNull(model);
  assertEquals("someKey2",model.getKey());
  assertEquals("bytes",new String(repositoryService.getModelEditorSource(model.getId()),"utf-8"));
  assertEquals(1,query.list().size());
  assertEquals(1,query.count());
}
