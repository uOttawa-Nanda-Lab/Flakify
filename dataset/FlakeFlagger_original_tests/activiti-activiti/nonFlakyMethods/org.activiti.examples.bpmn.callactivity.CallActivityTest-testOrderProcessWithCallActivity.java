@Deployment(resources={"org/activiti/examples/bpmn/callactivity/orderProcess.bpmn20.xml","org/activiti/examples/bpmn/callactivity/checkCreditProcess.bpmn20.xml"}) public void testOrderProcessWithCallActivity(){
  ProcessInstance pi=runtimeService.startProcessInstanceByKey("orderProcess");
  TaskQuery taskQuery=taskService.createTaskQuery();
  Task verifyCreditTask=taskQuery.singleResult();
  assertEquals("Verify credit history",verifyCreditTask.getName());
  ProcessInstance subProcessInstance=runtimeService.createProcessInstanceQuery().superProcessInstanceId(pi.getId()).singleResult();
  assertNotNull(subProcessInstance);
  assertEquals(pi.getId(),runtimeService.createProcessInstanceQuery().subProcessInstanceId(subProcessInstance.getId()).singleResult().getId());
  taskService.complete(verifyCreditTask.getId(),CollectionUtil.singletonMap("creditApproved",true));
  Task prepareAndShipTask=taskQuery.singleResult();
  assertEquals("Prepare and Ship",prepareAndShipTask.getName());
}
