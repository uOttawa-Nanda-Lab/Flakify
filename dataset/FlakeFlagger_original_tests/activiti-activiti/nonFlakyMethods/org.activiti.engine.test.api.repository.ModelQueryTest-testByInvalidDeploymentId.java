public void testByInvalidDeploymentId(){
  ModelQuery query=repositoryService.createModelQuery().deploymentId("invalid");
  assertNull(query.singleResult());
  assertEquals(0,query.count());
}
