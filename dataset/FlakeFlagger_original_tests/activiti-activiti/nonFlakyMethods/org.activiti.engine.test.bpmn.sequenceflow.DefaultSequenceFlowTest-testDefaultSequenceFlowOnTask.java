@Deployment public void testDefaultSequenceFlowOnTask(){
  String procId=runtimeService.startProcessInstanceByKey("defaultSeqFlow",CollectionUtil.singletonMap("input",2)).getId();
  assertNotNull(runtimeService.createExecutionQuery().processInstanceId(procId).activityId("task2").singleResult());
  procId=runtimeService.startProcessInstanceByKey("defaultSeqFlow",CollectionUtil.singletonMap("input",3)).getId();
  assertNotNull(runtimeService.createExecutionQuery().processInstanceId(procId).activityId("task3").singleResult());
  procId=runtimeService.startProcessInstanceByKey("defaultSeqFlow",CollectionUtil.singletonMap("input",123)).getId();
  assertNotNull(runtimeService.createExecutionQuery().processInstanceId(procId).activityId("task1").singleResult());
}
