@Deployment public void testChangeCategoryInDelegateTask(){
  Map<String,Object> variables=new HashMap<String,Object>();
  variables.put("approvers",Collections.singletonList("kermit"));
  ProcessInstance processInstance=runtimeService.startProcessInstanceByKey("delegateTaskTest",variables);
  List<Task> tasks=taskService.createTaskQuery().processInstanceId(processInstance.getId()).list();
  for (  Task task : tasks) {
    assertEquals("approval",task.getCategory());
    Map<String,Object> taskVariables=new HashMap<String,Object>();
    taskVariables.put("outcome","approve");
    taskService.complete(task.getId(),taskVariables,true);
  }
  assertEquals(0,taskService.createTaskQuery().processInstanceId(processInstance.getId()).count());
  for (  HistoricTaskInstance historicTaskInstance : historyService.createHistoricTaskInstanceQuery().list()) {
    assertEquals("approved",historicTaskInstance.getCategory());
  }
}
