@Deployment(resources={"org/activiti/engine/test/bpmn/usertask/DynamicUserTaskTest.basictask.bpmn20.xml"}) public void testChangeNameAndDescription(){
  ProcessInstance processInstance=runtimeService.startProcessInstanceByKey("dynamicUserTask");
  String processDefinitionId=processInstance.getProcessDefinitionId();
  Task task=taskService.createTaskQuery().processInstanceId(processInstance.getId()).singleResult();
  assertNull(task.getName());
  assertNull(task.getDescription());
  taskService.complete(task.getId());
  assertProcessEnded(processInstance.getId());
  ObjectNode infoNode=dynamicBpmnService.changeUserTaskName("task1","Task name test");
  dynamicBpmnService.changeUserTaskDescription("task1","Task description test",infoNode);
  dynamicBpmnService.saveProcessDefinitionInfo(processDefinitionId,infoNode);
  processInstance=runtimeService.startProcessInstanceByKey("dynamicUserTask");
  task=taskService.createTaskQuery().processInstanceId(processInstance.getId()).singleResult();
  assertEquals("Task name test",task.getName());
  assertEquals("Task description test",task.getDescription());
  taskService.complete(task.getId());
  assertProcessEnded(processInstance.getId());
}
