@Deployment(resources={"org/activiti/engine/test/db/processOne.bpmn20.xml"}) public void testStartProcessInstanceForSuspendedProcessDefinition(){
  ProcessDefinition processDefinition=repositoryService.createProcessDefinitionQuery().singleResult();
  repositoryService.suspendProcessDefinitionById(processDefinition.getId());
  try {
    runtimeService.startProcessInstanceById(processDefinition.getId());
    fail("Exception is expected but not thrown");
  }
 catch (  ActivitiException e) {
    assertTextPresentIgnoreCase("cannot start process instance",e.getMessage());
  }
  try {
    runtimeService.startProcessInstanceByKey(processDefinition.getKey());
    fail("Exception is expected but not thrown");
  }
 catch (  ActivitiException e) {
    assertTextPresentIgnoreCase("cannot start process instance",e.getMessage());
  }
}
