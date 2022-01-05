@Deployment public void testChangeFormKeyWithExpression(){
  Map<String,Object> varMap=new HashMap<String,Object>();
  varMap.put("start","test");
  ProcessInstance processInstance=runtimeService.startProcessInstanceByKey("dynamicUserTask",varMap);
  String processDefinitionId=processInstance.getProcessDefinitionId();
  Task task=taskService.createTaskQuery().processInstanceId(processInstance.getId()).singleResult();
  assertEquals("test",task.getFormKey());
  taskService.complete(task.getId());
  assertProcessEnded(processInstance.getId());
  ObjectNode infoNode=dynamicBpmnService.changeUserTaskFormKey("task1","${anotherKey}");
  dynamicBpmnService.saveProcessDefinitionInfo(processDefinitionId,infoNode);
  varMap=new HashMap<String,Object>();
  varMap.put("anotherKey","test2");
  processInstance=runtimeService.startProcessInstanceByKey("dynamicUserTask",varMap);
  task=taskService.createTaskQuery().processInstanceId(processInstance.getId()).singleResult();
  assertEquals("test2",task.getFormKey());
  taskService.complete(task.getId());
  assertProcessEnded(processInstance.getId());
}
