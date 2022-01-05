public void testByLatestVersion(){
  ModelQuery query=repositoryService.createModelQuery().latestVersion().modelKey("someKey");
  Model model=query.singleResult();
  assertNotNull(model);
  Model newVersion=repositoryService.newModel();
  newVersion.setName("my model");
  newVersion.setKey("someKey");
  newVersion.setCategory("test");
  newVersion.setVersion(model.getVersion() + 1);
  repositoryService.saveModel(newVersion);
  model=query.singleResult();
  assertNotNull(model);
  assertTrue(model.getVersion() == 2);
  repositoryService.deleteModel(model.getId());
}
