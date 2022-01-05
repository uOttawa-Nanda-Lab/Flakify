@Deployment(resources={"org/activiti/engine/test/api/task/TaskQueryTest.testProcessDefinition.bpmn20.xml"}) public void testTaskWithoutDueDate() throws Exception {
  ProcessInstance processInstance=runtimeService.startProcessInstanceByKey("oneTaskProcess");
  Task task=taskService.createTaskQuery().processInstanceId(processInstance.getId()).withoutTaskDueDate().singleResult();
  Date dueDate=new SimpleDateFormat("dd/MM/yyyy hh:mm:ss").parse("01/02/2003 01:12:13");
  task.setDueDate(dueDate);
  taskService.saveTask(task);
  assertEquals(0,taskService.createTaskQuery().processInstanceId(processInstance.getId()).withoutTaskDueDate().count());
  task=taskService.createTaskQuery().processInstanceId(processInstance.getId()).singleResult();
  task.setDueDate(null);
  taskService.saveTask(task);
  assertEquals(1,taskService.createTaskQuery().processInstanceId(processInstance.getId()).withoutTaskDueDate().count());
}
