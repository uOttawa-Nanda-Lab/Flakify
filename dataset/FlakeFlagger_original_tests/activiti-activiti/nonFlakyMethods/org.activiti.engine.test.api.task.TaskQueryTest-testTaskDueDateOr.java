@Deployment(resources={"org/activiti/engine/test/api/task/TaskQueryTest.testProcessDefinition.bpmn20.xml"}) public void testTaskDueDateOr() throws Exception {
  ProcessInstance processInstance=runtimeService.startProcessInstanceByKey("oneTaskProcess");
  Task task=taskService.createTaskQuery().processInstanceId(processInstance.getId()).singleResult();
  Date dueDate=new SimpleDateFormat("dd/MM/yyyy hh:mm:ss").parse("01/02/2003 01:12:13");
  task.setDueDate(dueDate);
  taskService.saveTask(task);
  assertEquals(1,taskService.createTaskQuery().processInstanceId(processInstance.getId()).or().taskId("invalid").taskDueDate(dueDate).count());
  Calendar otherDate=Calendar.getInstance();
  otherDate.add(Calendar.YEAR,1);
  assertEquals(0,taskService.createTaskQuery().processInstanceId(processInstance.getId()).or().taskId("invalid").taskDueDate(otherDate.getTime()).count());
  Calendar priorDate=Calendar.getInstance();
  priorDate.setTime(dueDate);
  priorDate.roll(Calendar.YEAR,-1);
  assertEquals(1,taskService.createTaskQuery().processInstanceId(processInstance.getId()).or().taskId("invalid").taskDueAfter(priorDate.getTime()).count());
  assertEquals(1,taskService.createTaskQuery().processInstanceId(processInstance.getId()).taskDueBefore(otherDate.getTime()).count());
}
