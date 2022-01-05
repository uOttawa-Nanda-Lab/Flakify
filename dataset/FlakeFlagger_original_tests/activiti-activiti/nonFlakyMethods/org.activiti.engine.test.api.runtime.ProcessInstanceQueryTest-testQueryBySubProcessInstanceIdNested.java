@Deployment(resources={"org/activiti/engine/test/api/runtime/superProcessWithNestedSubProcess.bpmn20.xml","org/activiti/engine/test/api/runtime/nestedSubProcess.bpmn20.xml","org/activiti/engine/test/api/runtime/subProcess.bpmn20.xml"}) public void testQueryBySubProcessInstanceIdNested(){
  ProcessInstance superProcessInstance=runtimeService.startProcessInstanceByKey("nestedSubProcessQueryTest");
  ProcessInstance subProcessInstance=runtimeService.createProcessInstanceQuery().superProcessInstanceId(superProcessInstance.getId()).singleResult();
  assertEquals(superProcessInstance.getId(),runtimeService.createProcessInstanceQuery().subProcessInstanceId(subProcessInstance.getId()).singleResult().getId());
  ProcessInstance nestedSubProcessInstance=runtimeService.createProcessInstanceQuery().superProcessInstanceId(subProcessInstance.getId()).singleResult();
  assertEquals(subProcessInstance.getId(),runtimeService.createProcessInstanceQuery().subProcessInstanceId(nestedSubProcessInstance.getId()).singleResult().getId());
}
