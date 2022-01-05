public void testUpdateModelPersistence() throws Exception {
  Model model=repositoryService.newModel();
  assertNotNull(model);
  model.setName("Test model");
  model.setCategory("test");
  model.setMetaInfo("meta");
  repositoryService.saveModel(model);
  assertNotNull(model.getId());
  model=repositoryService.getModel(model.getId());
  assertNotNull(model);
  model.setName("New name");
  model.setCategory("New category");
  model.setMetaInfo("test");
  model.setVersion(2);
  repositoryService.saveModel(model);
  assertNotNull(model.getId());
  repositoryService.addModelEditorSource(model.getId(),"new".getBytes("utf-8"));
  repositoryService.addModelEditorSourceExtra(model.getId(),"new".getBytes("utf-8"));
  model=repositoryService.getModel(model.getId());
  assertEquals("New name",model.getName());
  assertEquals("New category",model.getCategory());
  assertEquals("test",model.getMetaInfo());
  assertNotNull(model.getCreateTime());
  assertEquals(Integer.valueOf(2),model.getVersion());
  assertEquals("new",new String(repositoryService.getModelEditorSource(model.getId()),"utf-8"));
  assertEquals("new",new String(repositoryService.getModelEditorSourceExtra(model.getId()),"utf-8"));
  repositoryService.deleteModel(model.getId());
}
