@Deployment public void testJoinAfterSubprocesses(){
  Map<String,Object> variableMap=new HashMap<String,Object>();
  variableMap.put("a",1);
  variableMap.put("b",1);
  ProcessInstance processInstance=runtimeService.startProcessInstanceByKey("InclusiveGateway",variableMap);
  assertNotNull(processInstance.getId());
  List<Task> tasks=taskService.createTaskQuery().processInstanceId(processInstance.getId()).list();
  assertEquals(2,taskService.createTaskQuery().count());
  taskService.complete(tasks.get(0).getId());
  assertEquals(1,taskService.createTaskQuery().count());
  taskService.complete(tasks.get(1).getId());
  Task task=taskService.createTaskQuery().taskAssignee("c").singleResult();
  assertNotNull(task);
  taskService.complete(task.getId());
  processInstance=runtimeService.createProcessInstanceQuery().processInstanceId(processInstance.getId()).singleResult();
  assertNull(processInstance);
  variableMap=new HashMap<String,Object>();
  variableMap.put("a",1);
  variableMap.put("b",2);
  processInstance=runtimeService.startProcessInstanceByKey("InclusiveGateway",variableMap);
  assertNotNull(processInstance.getId());
  tasks=taskService.createTaskQuery().processInstanceId(processInstance.getId()).list();
  assertEquals(1,taskService.createTaskQuery().count());
  task=tasks.get(0);
  assertEquals("a",task.getAssignee());
  taskService.complete(task.getId());
  task=taskService.createTaskQuery().taskAssignee("c").singleResult();
  assertNotNull(task);
  taskService.complete(task.getId());
  processInstance=runtimeService.createProcessInstanceQuery().processInstanceId(processInstance.getId()).singleResult();
  assertNull(processInstance);
  variableMap=new HashMap<String,Object>();
  variableMap.put("a",2);
  variableMap.put("b",2);
  try {
    runtimeService.startProcessInstanceByKey("InclusiveGateway",variableMap);
    fail();
  }
 catch (  ActivitiException e) {
    assertTrue(e.getMessage().contains("No outgoing sequence flow"));
  }
}
