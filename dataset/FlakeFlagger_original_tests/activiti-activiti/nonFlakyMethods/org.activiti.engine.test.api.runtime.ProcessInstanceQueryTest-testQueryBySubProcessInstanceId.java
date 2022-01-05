@Deployment(resources={"org/activiti/engine/test/api/runtime/superProcess.bpmn20.xml","org/activiti/engine/test/api/runtime/subProcess.bpmn20.xml"}) public void testQueryBySubProcessInstanceId(){
  ProcessInstance superProcessInstance=runtimeService.startProcessInstanceByKey("subProcessQueryTest");
  ProcessInstance subProcessInstance=runtimeService.createProcessInstanceQuery().superProcessInstanceId(superProcessInstance.getId()).singleResult();
  assertNotNull(subProcessInstance);
  assertEquals(superProcessInstance.getId(),runtimeService.createProcessInstanceQuery().subProcessInstanceId(subProcessInstance.getId()).singleResult().getId());
}
