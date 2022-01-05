@Deployment(resources={"org/activiti/engine/test/api/task/TaskQueryTest.testProcessDefinition.bpmn20.xml"}) public void testTaskDueAfterOn() throws Exception {
  ProcessInstance processInstance=runtimeService.startProcessInstanceByKey("oneTaskProcess");
  Task task=taskService.createTaskQuery().processInstanceId(processInstance.getId()).singleResult();
  Calendar dueDateCal=Calendar.getInstance();
  task.setDueDate(dueDateCal.getTime());
  taskService.saveTask(task);
  Calendar oneHourAgo=Calendar.getInstance();
  oneHourAgo.setTime(dueDateCal.getTime());
  oneHourAgo.add(Calendar.HOUR,-1);
  Calendar oneHourLater=Calendar.getInstance();
  oneHourLater.setTime(dueDateCal.getTime());
  oneHourLater.add(Calendar.HOUR,1);
  assertEquals(1,taskService.createTaskQuery().processInstanceId(processInstance.getId()).or().taskId("invalid").taskDueAfter(oneHourAgo.getTime()).count());
  assertEquals(0,taskService.createTaskQuery().processInstanceId(processInstance.getId()).or().taskId("invalid").taskDueAfter(oneHourLater.getTime()).count());
  task=taskService.createTaskQuery().processInstanceId(processInstance.getId()).singleResult();
  task.setDueDate(null);
  taskService.saveTask(task);
  assertEquals(0,taskService.createTaskQuery().processInstanceId(processInstance.getId()).or().taskId("invalid").taskDueAfter(oneHourLater.getTime()).count());
  assertEquals(0,taskService.createTaskQuery().processInstanceId(processInstance.getId()).or().taskId("invalid").taskDueAfter(oneHourAgo.getTime()).count());
}
