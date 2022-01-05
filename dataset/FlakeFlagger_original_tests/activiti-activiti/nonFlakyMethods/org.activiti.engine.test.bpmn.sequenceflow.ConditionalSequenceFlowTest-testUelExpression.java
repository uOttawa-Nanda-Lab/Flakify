@Deployment public void testUelExpression(){
  Map<String,Object> variables=CollectionUtil.singletonMap("input","right");
  ProcessInstance pi=runtimeService.startProcessInstanceByKey("condSeqFlowUelExpr",variables);
  Task task=taskService.createTaskQuery().processInstanceId(pi.getId()).singleResult();
  assertEquals("task right",task.getName());
}
