public void testKeyAndLatest() throws Exception {
  ModelEntity model1=null;
  ModelEntity model2=null;
  try {
    model1=processEngineConfiguration.getModelEntityManager().create();
    model1.setKey("key1");
    model1.setVersion(1);
    repositoryService.saveModel(model1);
    model2=processEngineConfiguration.getModelEntityManager().create();
    model2.setKey("key2");
    model2.setVersion(2);
    repositoryService.saveModel(model2);
    Model model=repositoryService.createModelQuery().modelKey("key1").latestVersion().singleResult();
    Assert.assertNotNull(model);
  }
  finally {
    try {
      if (model1 != null) {
        repositoryService.deleteModel(model1.getId());
      }
      if (model2 != null) {
        repositoryService.deleteModel(model2.getId());
      }
    }
 catch (    Throwable ignore) {
    }
  }
}
