@Deployment(resources={"org/activiti/engine/test/bpmn/multiinstance/MultiInstanceTest.testParallelSubProcessAllAutomatic.bpmn20.xml"}) public void testParallelSubProcessAllAutomaticCompletionCondition(){
  String procId=runtimeService.startProcessInstanceByKey("miParallelSubprocessAllAutomatics",CollectionUtil.singletonMap(NR_OF_LOOPS_KEY,10)).getId();
  for (int i=0; i < 6; i++) {
    List<Execution> waitSubExecutions=runtimeService.createExecutionQuery().activityId("subProcessWait").list();
    assertTrue(waitSubExecutions.size() > 0);
    runtimeService.trigger(waitSubExecutions.get(0).getId());
  }
  List<Execution> waitSubExecutions=runtimeService.createExecutionQuery().activityId("subProcessWait").list();
  assertEquals(0,waitSubExecutions.size());
  Execution waitState=runtimeService.createExecutionQuery().activityId("waitState").singleResult();
  assertEquals(12,runtimeService.getVariable(procId,"sum"));
  runtimeService.trigger(waitState.getId());
  assertProcessEnded(procId);
}
