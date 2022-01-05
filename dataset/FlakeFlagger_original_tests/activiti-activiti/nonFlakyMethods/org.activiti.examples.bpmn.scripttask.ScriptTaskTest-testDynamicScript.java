@Deployment public void testDynamicScript(){
  ProcessInstance processInstance=runtimeService.startProcessInstanceByKey("testDynamicScript",CollectionUtil.map("a",20,"b",22));
  assertEquals(42,((Number)runtimeService.getVariable(processInstance.getId(),"test")).intValue());
  taskService.complete(taskService.createTaskQuery().singleResult().getId());
  assertProcessEnded(processInstance.getId());
  String processDefinitionId=processInstance.getProcessDefinitionId();
  ObjectNode infoNode=dynamicBpmnService.changeScriptTaskScript("script1","var sum = c + d;\nexecution.setVariable('test2', sum);");
  dynamicBpmnService.saveProcessDefinitionInfo(processDefinitionId,infoNode);
  processInstance=runtimeService.startProcessInstanceByKey("testDynamicScript",CollectionUtil.map("c",10,"d",12));
  assertEquals(22,((Number)runtimeService.getVariable(processInstance.getId(),"test2")).intValue());
  taskService.complete(taskService.createTaskQuery().singleResult().getId());
  assertProcessEnded(processInstance.getId());
}
