public void testNewModelPersistence(){
  Model model=repositoryService.newModel();
  assertNotNull(model);
  model.setName("Test model");
  model.setCategory("test");
  model.setMetaInfo("meta");
  repositoryService.saveModel(model);
  assertNotNull(model.getId());
  model=repositoryService.getModel(model.getId());
  assertNotNull(model);
  assertEquals("Test model",model.getName());
  assertEquals("test",model.getCategory());
  assertEquals("meta",model.getMetaInfo());
  assertNotNull(model.getCreateTime());
  assertEquals(Integer.valueOf(1),model.getVersion());
  repositoryService.deleteModel(model.getId());
}
