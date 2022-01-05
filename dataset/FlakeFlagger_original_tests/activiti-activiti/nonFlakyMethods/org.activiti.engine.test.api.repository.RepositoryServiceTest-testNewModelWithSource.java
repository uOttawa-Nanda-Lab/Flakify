public void testNewModelWithSource() throws Exception {
  Model model=repositoryService.newModel();
  model.setName("Test model");
  byte[] testSource="modelsource".getBytes("utf-8");
  repositoryService.saveModel(model);
  assertNotNull(model.getId());
  repositoryService.addModelEditorSource(model.getId(),testSource);
  model=repositoryService.getModel(model.getId());
  assertNotNull(model);
  assertEquals("Test model",model.getName());
  byte[] editorSourceBytes=repositoryService.getModelEditorSource(model.getId());
  assertEquals("modelsource",new String(editorSourceBytes,"utf-8"));
  repositoryService.deleteModel(model.getId());
}
