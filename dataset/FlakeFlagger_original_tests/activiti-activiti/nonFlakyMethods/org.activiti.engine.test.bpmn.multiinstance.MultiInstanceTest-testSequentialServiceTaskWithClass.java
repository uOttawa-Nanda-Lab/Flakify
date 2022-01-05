@Deployment public void testSequentialServiceTaskWithClass(){
  ProcessInstance procInst=runtimeService.startProcessInstanceByKey("multiInstanceServiceTask",CollectionUtil.singletonMap("result",5));
  Integer result=(Integer)runtimeService.getVariable(procInst.getId(),"result");
  assertEquals(160,result.intValue());
  Execution waitExecution=runtimeService.createExecutionQuery().processInstanceId(procInst.getId()).activityId("wait").singleResult();
  runtimeService.trigger(waitExecution.getId());
  assertProcessEnded(procInst.getId());
}
