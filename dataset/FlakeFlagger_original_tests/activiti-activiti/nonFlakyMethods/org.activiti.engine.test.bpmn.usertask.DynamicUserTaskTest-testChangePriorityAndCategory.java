@Deployment(resources={"org/activiti/engine/test/bpmn/usertask/DynamicUserTaskTest.assignment.bpmn20.xml"}) public void testChangePriorityAndCategory(){
  ProcessInstance processInstance=runtimeService.startProcessInstanceByKey("dynamicUserTask");
  String processDefinitionId=processInstance.getProcessDefinitionId();
  Task task=taskService.createTaskQuery().processInstanceId(processInstance.getId()).singleResult();
  assertEquals(50,task.getPriority());
  assertNull(task.getCategory());
  taskService.complete(task.getId());
  assertProcessEnded(processInstance.getId());
  ObjectNode infoNode=dynamicBpmnService.changeUserTaskPriority("task1","99");
  dynamicBpmnService.changeUserTaskCategory("task1","categoryTest",infoNode);
  dynamicBpmnService.saveProcessDefinitionInfo(processDefinitionId,infoNode);
  processInstance=runtimeService.startProcessInstanceByKey("dynamicUserTask");
  task=taskService.createTaskQuery().processInstanceId(processInstance.getId()).singleResult();
  assertEquals(99,task.getPriority());
  assertEquals("categoryTest",task.getCategory());
  taskService.complete(task.getId());
  assertProcessEnded(processInstance.getId());
}
