@Deployment(resources={"org/activiti/engine/test/bpmn/callactivity/CallActivity.testCallSimpleSubProcess.bpmn20.xml","org/activiti/engine/test/bpmn/callactivity/simpleSubProcess.bpmn20.xml"}) public void testCallSimpleSubProcess(){
  ProcessInstance processInstance=runtimeService.startProcessInstanceByKey("callSimpleSubProcess");
  TaskQuery taskQuery=taskService.createTaskQuery();
  Task taskBeforeSubProcess=taskQuery.singleResult();
  assertEquals("Task before subprocess",taskBeforeSubProcess.getName());
  taskService.complete(taskBeforeSubProcess.getId());
  Task taskInSubProcess=taskQuery.singleResult();
  assertEquals("Task in subprocess",taskInSubProcess.getName());
  taskService.complete(taskInSubProcess.getId());
  Task taskAfterSubProcess=taskQuery.singleResult();
  assertEquals("Task after subprocess",taskAfterSubProcess.getName());
  taskService.complete(taskAfterSubProcess.getId());
  assertProcessEnded(processInstance.getId());
  if (processEngineConfiguration.getHistoryLevel().isAtLeast(HistoryLevel.ACTIVITY)) {
    HistoricProcessInstance historicProcess=historyService.createHistoricProcessInstanceQuery().processInstanceId(taskInSubProcess.getProcessInstanceId()).singleResult();
    assertNotNull(historicProcess);
    assertEquals("theStart",historicProcess.getStartActivityId());
    List<HistoricActivityInstance> historicInstances=historyService.createHistoricActivityInstanceQuery().processInstanceId(taskInSubProcess.getProcessInstanceId()).list();
    assertEquals(3L,historicInstances.size());
    Set<String> expectedActivities=new HashSet<String>(Arrays.asList(new String[]{"theStart","task","theEnd"}));
    for (    HistoricActivityInstance act : historicInstances) {
      expectedActivities.remove(act.getActivityId());
    }
    assertTrue("Not all expected activities were found in the history",expectedActivities.isEmpty());
  }
}
