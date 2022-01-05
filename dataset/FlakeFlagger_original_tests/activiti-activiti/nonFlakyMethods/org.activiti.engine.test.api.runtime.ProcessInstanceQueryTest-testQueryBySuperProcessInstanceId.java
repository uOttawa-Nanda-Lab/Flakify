@Deployment(resources={"org/activiti/engine/test/api/runtime/superProcess.bpmn20.xml","org/activiti/engine/test/api/runtime/subProcess.bpmn20.xml"}) public void testQueryBySuperProcessInstanceId(){
  ProcessInstance superProcessInstance=runtimeService.startProcessInstanceByKey("subProcessQueryTest");
  ProcessInstanceQuery query=runtimeService.createProcessInstanceQuery().superProcessInstanceId(superProcessInstance.getId());
  ProcessInstance subProcessInstance=query.singleResult();
  assertNotNull(subProcessInstance);
  assertEquals(1,query.list().size());
  assertEquals(1,query.count());
}
