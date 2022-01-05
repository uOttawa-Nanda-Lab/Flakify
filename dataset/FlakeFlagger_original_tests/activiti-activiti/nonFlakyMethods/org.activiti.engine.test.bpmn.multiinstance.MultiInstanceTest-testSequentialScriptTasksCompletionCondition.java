@Deployment public void testSequentialScriptTasksCompletionCondition(){
  runtimeService.startProcessInstanceByKey("miSequentialScriptTaskCompletionCondition").getId();
  List<Execution> executions=runtimeService.createExecutionQuery().list();
  assertEquals(2,executions.size());
  Execution processInstanceExecution=null;
  Execution waitStateExecution=null;
  for (  Execution execution : executions) {
    if (execution.getId().equals(execution.getProcessInstanceId())) {
      processInstanceExecution=execution;
    }
 else {
      waitStateExecution=execution;
    }
  }
  assertNotNull(processInstanceExecution);
  assertNotNull(waitStateExecution);
  int sum=(Integer)runtimeService.getVariable(waitStateExecution.getId(),"sum");
  assertEquals(5,sum);
}
