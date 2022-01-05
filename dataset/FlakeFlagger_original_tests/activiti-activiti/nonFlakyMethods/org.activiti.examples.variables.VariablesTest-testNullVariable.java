@Deployment public void testNullVariable(){
  ProcessInstance processInstance=runtimeService.startProcessInstanceByKey("taskAssigneeProcess");
  Task task=taskService.createTaskQuery().processInstanceId(processInstance.getId()).singleResult();
  Map<String,Object> variables=new HashMap<String,Object>();
  variables.put("testProperty","434");
  taskService.complete(task.getId(),variables);
  String resultVar=(String)runtimeService.getVariable(processInstance.getId(),"testProperty");
  assertEquals("434",resultVar);
  task=taskService.createTaskQuery().singleResult();
  taskService.complete(task.getId());
  processInstance=runtimeService.startProcessInstanceByKey("taskAssigneeProcess");
  task=taskService.createTaskQuery().processInstanceId(processInstance.getId()).singleResult();
  variables=new HashMap<String,Object>();
  try {
    taskService.complete(task.getId(),variables);
    fail("Should throw exception as testProperty is not defined and used in Script task");
  }
 catch (  Exception e) {
    runtimeService.deleteProcessInstance(processInstance.getId(),"intentional exception in script task");
    assertEquals("class org.activiti.engine.ActivitiException",e.getClass().toString());
  }
  processInstance=runtimeService.startProcessInstanceByKey("taskAssigneeProcess");
  task=taskService.createTaskQuery().processInstanceId(processInstance.getId()).singleResult();
  variables=new HashMap<String,Object>();
  variables.put("testProperty",null);
  try {
    taskService.complete(task.getId(),variables);
  }
 catch (  Exception e) {
    fail("Should not throw exception as the testProperty is defined, although null");
  }
  resultVar=(String)runtimeService.getVariable(processInstance.getId(),"testProperty");
  assertNull(resultVar);
  runtimeService.deleteProcessInstance(processInstance.getId(),"intentional exception in script task");
}
