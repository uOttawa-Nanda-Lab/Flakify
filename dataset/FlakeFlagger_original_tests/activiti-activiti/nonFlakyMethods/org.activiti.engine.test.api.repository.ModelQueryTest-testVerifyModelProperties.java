public void testVerifyModelProperties(){
  List<Model> models=repositoryService.createModelQuery().orderByModelName().asc().list();
  Model modelOne=models.get(0);
  assertEquals("my model",modelOne.getName());
  assertEquals(modelOneId,modelOne.getId());
  models=repositoryService.createModelQuery().modelNameLike("%model%").orderByModelName().asc().list();
  assertEquals("my model",models.get(0).getName());
  assertEquals(1,models.size());
  assertEquals(1,repositoryService.createModelQuery().orderByModelId().asc().list().size());
}
