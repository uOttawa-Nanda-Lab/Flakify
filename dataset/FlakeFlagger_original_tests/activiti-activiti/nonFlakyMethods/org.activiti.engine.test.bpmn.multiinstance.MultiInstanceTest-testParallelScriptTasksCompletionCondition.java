@Deployment public void testParallelScriptTasksCompletionCondition(){
  ProcessInstance processInstance=runtimeService.startProcessInstanceByKey("miParallelScriptTaskCompletionCondition");
  Execution waitStateExecution=runtimeService.createExecutionQuery().activityId("waitState").singleResult();
  assertNotNull(waitStateExecution);
  int sum=(Integer)runtimeService.getVariable(processInstance.getId(),"sum");
  assertEquals(2,sum);
  runtimeService.trigger(waitStateExecution.getId());
  assertProcessEnded(processInstance.getId());
}
