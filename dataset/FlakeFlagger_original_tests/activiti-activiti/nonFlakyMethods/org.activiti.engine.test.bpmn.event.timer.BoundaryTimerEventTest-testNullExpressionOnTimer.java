@Deployment public void testNullExpressionOnTimer(){
  HashMap<String,Object> variables=new HashMap<String,Object>();
  variables.put("duration",null);
  ProcessInstance pi=runtimeService.startProcessInstanceByKey("testNullExpressionOnTimer",variables);
  TimerJobQuery jobQuery=managementService.createTimerJobQuery().processInstanceId(pi.getId());
  List<Job> jobs=jobQuery.list();
  assertEquals(0,jobs.size());
  ProcessInstance processInstance=processEngine.getRuntimeService().createProcessInstanceQuery().processInstanceId(pi.getId()).singleResult();
  assertNotNull(processInstance);
}
