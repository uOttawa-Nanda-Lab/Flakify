public void testQueryByInvalidDeploymentId(){
  assertNull(runtimeService.createProcessInstanceQuery().deploymentId("invalid").singleResult());
  assertEquals(0,runtimeService.createProcessInstanceQuery().deploymentId("invalid").count());
}
