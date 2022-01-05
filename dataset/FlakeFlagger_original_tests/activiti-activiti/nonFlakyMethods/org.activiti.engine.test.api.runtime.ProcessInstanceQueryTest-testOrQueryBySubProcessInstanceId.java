@Deployment(resources={"org/activiti/engine/test/api/runtime/superProcess.bpmn20.xml","org/activiti/engine/test/api/runtime/subProcess.bpmn20.xml"}) public void testOrQueryBySubProcessInstanceId(){
  ProcessInstance superProcessInstance=runtimeService.startProcessInstanceByKey("subProcessQueryTest");
  ProcessInstance subProcessInstance=runtimeService.createProcessInstanceQuery().or().superProcessInstanceId(superProcessInstance.getId()).processDefinitionId("undefined").singleResult();
  assertNotNull(subProcessInstance);
  assertEquals(superProcessInstance.getId(),runtimeService.createProcessInstanceQuery().or().subProcessInstanceId(subProcessInstance.getId()).processDefinitionId("undefined").singleResult().getId());
}
