@Deployment public void testChangeClassName(){
  Map<String,Object> varMap=new HashMap<String,Object>();
  varMap.put("count",0);
  varMap.put("count2",0);
  ProcessInstance processInstance=runtimeService.startProcessInstanceByKey("dynamicServiceTask",varMap);
  Task task=taskService.createTaskQuery().processInstanceId(processInstance.getId()).singleResult();
  taskService.complete(task.getId());
  assertEquals(1,runtimeService.getVariable(processInstance.getId(),"count"));
  assertEquals(0,runtimeService.getVariable(processInstance.getId(),"count2"));
  task=taskService.createTaskQuery().processInstanceId(processInstance.getId()).singleResult();
  taskService.complete(task.getId());
  assertProcessEnded(processInstance.getId());
  varMap=new HashMap<String,Object>();
  varMap.put("count",0);
  varMap.put("count2",0);
  processInstance=runtimeService.startProcessInstanceByKey("dynamicServiceTask",varMap);
  String processDefinitionId=processInstance.getProcessDefinitionId();
  ObjectNode infoNode=dynamicBpmnService.changeServiceTaskClassName("service","org.activiti.engine.test.bpmn.servicetask.DummyServiceTask2");
  dynamicBpmnService.saveProcessDefinitionInfo(processDefinitionId,infoNode);
  task=taskService.createTaskQuery().processInstanceId(processInstance.getId()).singleResult();
  taskService.complete(task.getId());
  assertEquals(1,runtimeService.getVariable(processInstance.getId(),"count"));
  assertEquals(0,runtimeService.getVariable(processInstance.getId(),"count2"));
  task=taskService.createTaskQuery().processInstanceId(processInstance.getId()).singleResult();
  taskService.complete(task.getId());
  assertProcessEnded(processInstance.getId());
}
