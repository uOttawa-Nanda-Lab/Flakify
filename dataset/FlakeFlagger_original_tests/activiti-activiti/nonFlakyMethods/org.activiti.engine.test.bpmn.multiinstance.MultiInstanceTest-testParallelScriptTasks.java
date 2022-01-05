@Deployment public void testParallelScriptTasks(){
  Map<String,Object> vars=new HashMap<String,Object>();
  vars.put("sum",0);
  vars.put(NR_OF_LOOPS_KEY,10);
  runtimeService.startProcessInstanceByKey("miParallelScriptTask",vars);
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
  assertEquals(45,sum);
}
