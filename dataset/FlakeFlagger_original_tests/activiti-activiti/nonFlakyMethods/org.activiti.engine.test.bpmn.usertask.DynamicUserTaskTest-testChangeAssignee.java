@Deployment(resources={"org/activiti/engine/test/bpmn/usertask/DynamicUserTaskTest.assignment.bpmn20.xml"}) public void testChangeAssignee(){
  ProcessInstance processInstance=runtimeService.startProcessInstanceByKey("dynamicUserTask");
  String processDefinitionId=processInstance.getProcessDefinitionId();
  Task task=taskService.createTaskQuery().processInstanceId(processInstance.getId()).singleResult();
  assertEquals("test",task.getAssignee());
  taskService.complete(task.getId());
  assertProcessEnded(processInstance.getId());
  ObjectNode infoNode=dynamicBpmnService.changeUserTaskAssignee("task1","test2");
  dynamicBpmnService.saveProcessDefinitionInfo(processDefinitionId,infoNode);
  processInstance=runtimeService.startProcessInstanceByKey("dynamicUserTask");
  task=taskService.createTaskQuery().processInstanceId(processInstance.getId()).singleResult();
  assertEquals("test2",task.getAssignee());
  taskService.complete(task.getId());
  assertProcessEnded(processInstance.getId());
}
