public void testOrQueryByInvalidDeploymentId(){
  assertNull(runtimeService.createProcessInstanceQuery().or().deploymentId("invalid").processDefinitionId("undefined").endOr().singleResult());
  assertEquals(0,runtimeService.createProcessInstanceQuery().or().deploymentId("invalid").processDefinitionId("undefined").endOr().count());
}
