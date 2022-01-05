@Deployment(resources={"org/activiti/engine/test/api/runtime/oneTaskProcess.bpmn20.xml"}) public void testSuspendProcessInstancesDuringProcessDefinitionSuspend(){
  int nrOfProcessInstances=9;
  ProcessDefinition processDefinition=repositoryService.createProcessDefinitionQuery().singleResult();
  for (int i=0; i < nrOfProcessInstances; i++) {
    runtimeService.startProcessInstanceByKey(processDefinition.getKey());
  }
  assertEquals(nrOfProcessInstances,runtimeService.createProcessInstanceQuery().count());
  assertEquals(0,runtimeService.createProcessInstanceQuery().suspended().count());
  assertEquals(nrOfProcessInstances,runtimeService.createProcessInstanceQuery().active().count());
  repositoryService.suspendProcessDefinitionById(processDefinition.getId(),true,null);
  for (  ProcessInstance processInstance : runtimeService.createProcessInstanceQuery().list()) {
    assertTrue(processInstance.isSuspended());
  }
  for (  Task task : taskService.createTaskQuery().list()) {
    try {
      taskService.complete(task.getId());
      fail("A suspended task shouldn't be able to be continued");
    }
 catch (    ActivitiException e) {
    }
  }
  assertEquals(nrOfProcessInstances,runtimeService.createProcessInstanceQuery().count());
  assertEquals(nrOfProcessInstances,runtimeService.createProcessInstanceQuery().suspended().count());
  assertEquals(0,runtimeService.createProcessInstanceQuery().active().count());
  repositoryService.activateProcessDefinitionById(processDefinition.getId(),true,null);
  for (  Task task : taskService.createTaskQuery().list()) {
    taskService.complete(task.getId());
  }
  assertEquals(0,runtimeService.createProcessInstanceQuery().count());
  assertEquals(0,runtimeService.createProcessInstanceQuery().suspended().count());
  assertEquals(0,runtimeService.createProcessInstanceQuery().active().count());
}
