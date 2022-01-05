@Deployment public void testAsyncScript(){
  ProcessInstance processInstance=runtimeService.startProcessInstanceByKey("asyncScript");
  assertEquals(1,managementService.createJobQuery().count());
  List<Execution> executions=runtimeService.createExecutionQuery().processInstanceId(processInstance.getId()).list();
  String eid=null;
  for (  Execution e : executions) {
    if (e.getParentId() != null) {
      eid=e.getId();
    }
  }
  assertNull(runtimeService.getVariable(eid,"invoked"));
  waitForJobExecutorToProcessAllJobs(5000L,100L);
  assertEquals(0,managementService.createJobQuery().count());
  assertEquals("true",runtimeService.getVariable(eid,"invoked"));
  runtimeService.trigger(eid);
}
