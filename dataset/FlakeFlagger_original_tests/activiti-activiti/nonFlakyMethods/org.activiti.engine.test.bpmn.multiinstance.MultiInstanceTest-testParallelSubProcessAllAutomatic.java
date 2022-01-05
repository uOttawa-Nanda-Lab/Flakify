@Deployment public void testParallelSubProcessAllAutomatic(){
  String procId=runtimeService.startProcessInstanceByKey("miParallelSubprocessAllAutomatics",CollectionUtil.singletonMap(NR_OF_LOOPS_KEY,5)).getId();
  for (int i=0; i < 5; i++) {
    List<Execution> waitSubExecutions=runtimeService.createExecutionQuery().activityId("subProcessWait").list();
    assertTrue(waitSubExecutions.size() > 0);
    runtimeService.trigger(waitSubExecutions.get(0).getId());
  }
  List<Execution> waitSubExecutions=runtimeService.createExecutionQuery().activityId("subProcessWait").list();
  assertEquals(0,waitSubExecutions.size());
  Execution waitState=runtimeService.createExecutionQuery().activityId("waitState").singleResult();
  assertEquals(10,runtimeService.getVariable(waitState.getId(),"sum"));
  runtimeService.trigger(waitState.getId());
  assertProcessEnded(procId);
}
