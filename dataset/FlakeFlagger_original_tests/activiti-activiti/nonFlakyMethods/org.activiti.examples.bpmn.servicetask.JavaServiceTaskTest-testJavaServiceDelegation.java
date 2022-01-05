@Deployment public void testJavaServiceDelegation(){
  ProcessInstance pi=runtimeService.startProcessInstanceByKey("javaServiceDelegation",CollectionUtil.singletonMap("input","Activiti BPM Engine"));
  Execution execution=runtimeService.createExecutionQuery().processInstanceId(pi.getId()).activityId("waitState").singleResult();
  assertEquals("ACTIVITI BPM ENGINE",runtimeService.getVariable(execution.getId(),"input"));
}
