@Deployment(resources={"org/activiti/engine/test/bpmn/event/compensate/CompensateEventTest.testCompensateWithSubprocess.bpmn20.xml"}) public void testCompensateWithSubprocess2(){
  ProcessInstance processInstance=runtimeService.startProcessInstanceByKey("compensateProcess",CollectionUtil.singletonMap("doCompensation",false));
  Task afterBookHotelTask=taskService.createTaskQuery().processInstanceId(processInstance.getId()).taskDefinitionKey("afterBookHotel").singleResult();
  taskService.complete(afterBookHotelTask.getId());
  assertProcessEnded(processInstance.getId());
}
