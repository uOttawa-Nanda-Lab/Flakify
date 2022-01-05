public void testModelProperties(){
  ModelQuery query=repositoryService.createModelQuery();
  Model model=query.singleResult();
  assertNotNull(model.getId());
  assertNotNull(model.getCategory());
  assertNotNull(model.getKey());
  assertNotNull(model.getName());
  assertNotNull(model.getVersion());
  assertNotNull(model.getCreateTime());
  assertNotNull(model.getLastUpdateTime());
}
