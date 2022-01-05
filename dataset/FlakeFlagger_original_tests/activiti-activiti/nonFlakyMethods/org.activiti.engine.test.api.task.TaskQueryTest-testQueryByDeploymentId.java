@Deployment(resources={"org/activiti/engine/test/api/task/TaskQueryTest.testProcessDefinition.bpmn20.xml"}) public void testQueryByDeploymentId() throws Exception {
  org.activiti.engine.repository.Deployment deployment=repositoryService.createDeploymentQuery().singleResult();
  runtimeService.startProcessInstanceByKey("oneTaskProcess");
  assertNotNull(taskService.createTaskQuery().deploymentId(deployment.getId()).singleResult());
  assertEquals(1,taskService.createTaskQuery().deploymentId(deployment.getId()).count());
  assertNull(taskService.createTaskQuery().deploymentId("invalid").singleResult());
  assertEquals(0,taskService.createTaskQuery().deploymentId("invalid").count());
}
