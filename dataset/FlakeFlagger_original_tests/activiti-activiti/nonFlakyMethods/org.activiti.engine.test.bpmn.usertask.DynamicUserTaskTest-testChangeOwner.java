@Deployment(resources={"org/activiti/engine/test/bpmn/usertask/DynamicUserTaskTest.assignment.bpmn20.xml"}) public void testChangeOwner(){
  ProcessInstance processInstance=runtimeService.startProcessInstanceByKey("dynamicUserTask");
  String processDefinitionId=processInstance.getProcessDefinitionId();
  Task task=taskService.createTaskQuery().processInstanceId(processInstance.getId()).singleResult();
  assertEquals("ownerTest",task.getOwner());
  taskService.complete(task.getId());
  assertProcessEnded(processInstance.getId());
  ObjectNode infoNode=dynamicBpmnService.changeUserTaskOwner("task1","ownerTest2");
  dynamicBpmnService.saveProcessDefinitionInfo(processDefinitionId,infoNode);
  processInstance=runtimeService.startProcessInstanceByKey("dynamicUserTask");
  task=taskService.createTaskQuery().processInstanceId(processInstance.getId()).singleResult();
  assertEquals("ownerTest2",task.getOwner());
  taskService.complete(task.getId());
  assertProcessEnded(processInstance.getId());
}
