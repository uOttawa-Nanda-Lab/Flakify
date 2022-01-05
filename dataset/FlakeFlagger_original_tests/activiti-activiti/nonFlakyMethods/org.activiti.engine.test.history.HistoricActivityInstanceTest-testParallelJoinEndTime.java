/** 
 * Test to validate fix for ACT-1549: endTime of joining parallel gateway is not set
 */
@Deployment public void testParallelJoinEndTime(){
  ProcessInstance processInstance=runtimeService.startProcessInstanceByKey("forkJoin");
  List<Task> tasksToComplete=taskService.createTaskQuery().processInstanceId(processInstance.getId()).list();
  assertEquals(2,tasksToComplete.size());
  taskService.complete(tasksToComplete.get(0).getId());
  taskService.complete(tasksToComplete.get(1).getId());
  List<HistoricActivityInstance> historicActivityInstance=historyService.createHistoricActivityInstanceQuery().activityId("join").processInstanceId(processInstance.getId()).list();
  assertNotNull(historicActivityInstance);
  assertEquals(2,historicActivityInstance.size());
  assertNotNull(historicActivityInstance.get(0).getEndTime());
  assertNotNull(historicActivityInstance.get(1).getEndTime());
}
