@Deployment public void testHistoricActivityInstanceProperties(){
  runtimeService.startProcessInstanceByKey("taskAssigneeProcess");
  HistoricActivityInstance historicActivityInstance=historyService.createHistoricActivityInstanceQuery().activityId("theTask").singleResult();
  Task task=taskService.createTaskQuery().singleResult();
  assertEquals(task.getId(),historicActivityInstance.getTaskId());
  assertEquals("kermit",historicActivityInstance.getAssignee());
}
