@Deployment(resources={"org/activiti/engine/test/api/runtime/superProcessWithNestedSubProcess.bpmn20.xml","org/activiti/engine/test/api/runtime/nestedSubProcess.bpmn20.xml","org/activiti/engine/test/api/runtime/subProcess.bpmn20.xml"}) public void testQueryBySuperProcessInstanceIdNested(){
  ProcessInstance superProcessInstance=runtimeService.startProcessInstanceByKey("nestedSubProcessQueryTest");
  ProcessInstance subProcessInstance=runtimeService.createProcessInstanceQuery().superProcessInstanceId(superProcessInstance.getId()).singleResult();
  assertNotNull(subProcessInstance);
  ProcessInstance nestedSubProcessInstance=runtimeService.createProcessInstanceQuery().superProcessInstanceId(subProcessInstance.getId()).singleResult();
  assertNotNull(nestedSubProcessInstance);
}
