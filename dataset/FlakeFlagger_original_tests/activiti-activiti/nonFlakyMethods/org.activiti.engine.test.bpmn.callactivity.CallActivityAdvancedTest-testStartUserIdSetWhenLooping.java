@Deployment(resources={"org/activiti/engine/test/bpmn/callactivity/CallActivity.testStartUserIdSetWhenLooping.bpmn20.xml","org/activiti/engine/test/bpmn/callactivity/simpleSubProcess.bpmn20.xml"}) public void testStartUserIdSetWhenLooping(){
  Authentication.setAuthenticatedUserId("kermit");
  ProcessInstance processInstance=runtimeService.startProcessInstanceByKey("loopingCallActivity",CollectionUtil.singletonMap("input",0));
  for (int i=1; i < 4; i++) {
    Task task=taskService.createTaskQuery().singleResult();
    assertEquals("Task in subprocess",task.getName());
    Authentication.setAuthenticatedUserId("kermit");
    taskService.complete(task.getId(),CollectionUtil.singletonMap("input",i));
  }
  Authentication.setAuthenticatedUserId(null);
  Task task=taskService.createTaskQuery().singleResult();
  assertEquals("Final task",task.getName());
  if (processEngineConfiguration.getHistoryLevel().isAtLeast(HistoryLevel.ACTIVITY)) {
    List<HistoricProcessInstance> historicProcessInstances=historyService.createHistoricProcessInstanceQuery().superProcessInstanceId(processInstance.getId()).list();
    assertEquals(3,historicProcessInstances.size());
    for (    HistoricProcessInstance historicProcessInstance : historicProcessInstances) {
      assertNotNull(historicProcessInstance.getStartUserId());
      assertNotNull(historicProcessInstance.getStartTime());
      assertNotNull(historicProcessInstance.getEndTime());
    }
  }
}
