@Deployment public void testReceiveTaskWithBoundaryTimer(){
  HashMap<String,Object> variables=new HashMap<String,Object>();
  variables.put("timeCycle","R/PT1H");
  ProcessInstance pi=runtimeService.startProcessInstanceByKey("nonInterruptingCycle",variables);
  TimerJobQuery jobQuery=managementService.createTimerJobQuery().processInstanceId(pi.getId());
  List<Job> jobs=jobQuery.list();
  assertEquals(1,jobs.size());
  List<Execution> executions=runtimeService.createExecutionQuery().activityId("task").list();
  assertEquals(1,executions.size());
  List<String> activeActivityIds=runtimeService.getActiveActivityIds(executions.get(0).getId());
  assertEquals(2,activeActivityIds.size());
  Collections.sort(activeActivityIds);
  assertEquals("task",activeActivityIds.get(0));
  assertEquals("timer",activeActivityIds.get(1));
  runtimeService.trigger(executions.get(0).getId());
  assertProcessEnded(pi.getId());
}
