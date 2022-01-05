@Deployment public void testSequentialServiceTaskWithClassAndCollection(){
  Collection<Integer> items=Arrays.asList(1,2,3,4,5,6);
  Map<String,Object> vars=new HashMap<String,Object>();
  vars.put("result",1);
  vars.put("items",items);
  ProcessInstance procInst=runtimeService.startProcessInstanceByKey("multiInstanceServiceTask",vars);
  Integer result=(Integer)runtimeService.getVariable(procInst.getId(),"result");
  assertEquals(720,result.intValue());
  Execution waitExecution=runtimeService.createExecutionQuery().processInstanceId(procInst.getId()).activityId("wait").singleResult();
  runtimeService.trigger(waitExecution.getId());
  assertProcessEnded(procInst.getId());
}
