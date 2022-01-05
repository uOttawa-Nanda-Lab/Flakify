public void testModelTenancy(){
  int nrOfModelsWithTenant=3;
  for (int i=0; i < nrOfModelsWithTenant; i++) {
    Model model=repositoryService.newModel();
    model.setName(i + "");
    model.setTenantId(TEST_TENANT_ID);
    repositoryService.saveModel(model);
  }
  int nrOfModelsWithoutTenant=5;
  for (int i=0; i < nrOfModelsWithoutTenant; i++) {
    Model model=repositoryService.newModel();
    model.setName(i + "");
    repositoryService.saveModel(model);
  }
  assertEquals(nrOfModelsWithoutTenant + nrOfModelsWithTenant,repositoryService.createModelQuery().list().size());
  assertEquals(nrOfModelsWithoutTenant,repositoryService.createModelQuery().modelWithoutTenantId().list().size());
  assertEquals(nrOfModelsWithTenant,repositoryService.createModelQuery().modelTenantId(TEST_TENANT_ID).list().size());
  assertEquals(nrOfModelsWithTenant,repositoryService.createModelQuery().modelTenantIdLike("my%").list().size());
  assertEquals(0,repositoryService.createModelQuery().modelTenantId("a%").list().size());
  for (  Model model : repositoryService.createModelQuery().list()) {
    repositoryService.deleteModel(model.getId());
  }
}
