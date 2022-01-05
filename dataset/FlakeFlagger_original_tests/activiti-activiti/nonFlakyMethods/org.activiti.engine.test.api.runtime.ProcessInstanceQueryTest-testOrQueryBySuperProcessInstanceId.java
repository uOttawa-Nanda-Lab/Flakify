@Deployment(resources={"org/activiti/engine/test/api/runtime/superProcess.bpmn20.xml","org/activiti/engine/test/api/runtime/subProcess.bpmn20.xml"}) public void testOrQueryBySuperProcessInstanceId(){
  ProcessInstance superProcessInstance=runtimeService.startProcessInstanceByKey("subProcessQueryTest");
  ProcessInstanceQuery query=runtimeService.createProcessInstanceQuery().or().superProcessInstanceId(superProcessInstance.getId()).processDefinitionId("undefined").endOr();
  ProcessInstance subProcessInstance=query.singleResult();
  assertNotNull(subProcessInstance);
  assertEquals(1,query.list().size());
  assertEquals(1,query.count());
}
