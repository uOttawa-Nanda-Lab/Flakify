@Deployment public void testDynamicExpression(){
  Map<String,Object> variables=CollectionUtil.singletonMap("input","right");
  ProcessInstance pi=runtimeService.startProcessInstanceByKey("condSeqFlowUelExpr",variables);
  Task task=taskService.createTaskQuery().processInstanceId(pi.getId()).singleResult();
  assertEquals("task not left",task.getName());
  taskService.complete(task.getId());
  ObjectNode infoNode=dynamicBpmnService.changeSequenceFlowCondition("flow1","${input == 'right'}");
  dynamicBpmnService.changeSequenceFlowCondition("flow2","${input != 'right'}",infoNode);
  dynamicBpmnService.saveProcessDefinitionInfo(pi.getProcessDefinitionId(),infoNode);
  pi=runtimeService.startProcessInstanceByKey("condSeqFlowUelExpr",variables);
  task=taskService.createTaskQuery().processInstanceId(pi.getId()).singleResult();
  assertEquals("task left",task.getName());
  taskService.complete(task.getId());
  variables=CollectionUtil.singletonMap("input","right2");
  pi=runtimeService.startProcessInstanceByKey("condSeqFlowUelExpr",variables);
  task=taskService.createTaskQuery().processInstanceId(pi.getId()).singleResult();
  assertEquals("task not left",task.getName());
  taskService.complete(task.getId());
}
