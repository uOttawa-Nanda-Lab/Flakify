public void testSubTaskDeleteOnProcessInstanceDelete(){
  Deployment deployment=repositoryService.createDeployment().addClasspathResource("org/activiti/engine/test/api/runtime/oneTaskProcess.bpmn20.xml").deploy();
  ProcessInstance processInstance=runtimeService.startProcessInstanceByKey("oneTaskProcess");
  Task task=taskService.createTaskQuery().processInstanceId(processInstance.getId()).singleResult();
  taskService.setAssignee(task.getId(),"test");
  Task subTask1=taskService.newTask();
  subTask1.setName("Sub task 1");
  subTask1.setParentTaskId(task.getId());
  subTask1.setAssignee("test");
  taskService.saveTask(subTask1);
  Task subTask2=taskService.newTask();
  subTask2.setName("Sub task 2");
  subTask2.setParentTaskId(task.getId());
  subTask2.setAssignee("test");
  taskService.saveTask(subTask2);
  List<Task> tasks=taskService.createTaskQuery().taskAssignee("test").list();
  assertEquals(3,tasks.size());
  runtimeService.deleteProcessInstance(processInstance.getId(),"none");
  tasks=taskService.createTaskQuery().taskAssignee("test").list();
  assertEquals(0,tasks.size());
  if (processEngineConfiguration.getHistoryLevel().isAtLeast(HistoryLevel.ACTIVITY)) {
    List<HistoricTaskInstance> historicTasks=historyService.createHistoricTaskInstanceQuery().taskAssignee("test").list();
    assertEquals(3,historicTasks.size());
    historyService.deleteHistoricProcessInstance(processInstance.getId());
    historicTasks=historyService.createHistoricTaskInstanceQuery().taskAssignee("test").list();
    assertEquals(0,historicTasks.size());
  }
  repositoryService.deleteDeployment(deployment.getId(),true);
}
