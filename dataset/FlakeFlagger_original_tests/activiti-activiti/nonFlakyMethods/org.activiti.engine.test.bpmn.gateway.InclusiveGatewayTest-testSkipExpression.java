@Deployment public void testSkipExpression(){
  Map<String,Object> varMap=new HashMap<String,Object>();
  varMap.put("_ACTIVITI_SKIP_EXPRESSION_ENABLED",true);
  varMap.put("input",10);
  ProcessInstance processInstance=runtimeService.startProcessInstanceByKey("inclusiveGwSkipExpression",varMap);
  Task task=taskService.createTaskQuery().singleResult();
  assertNotNull(task);
  assertEquals("theTask1",task.getTaskDefinitionKey());
  taskService.complete(task.getId());
  assertEquals(0,runtimeService.createProcessInstanceQuery().processInstanceId(processInstance.getId()).count());
  varMap=new HashMap<String,Object>();
  varMap.put("_ACTIVITI_SKIP_EXPRESSION_ENABLED",true);
  varMap.put("input",30);
  processInstance=runtimeService.startProcessInstanceByKey("inclusiveGwSkipExpression",varMap);
  List<Task> tasks=taskService.createTaskQuery().list();
  assertEquals(2,tasks.size());
  taskService.complete(tasks.get(0).getId());
  taskService.complete(tasks.get(1).getId());
  assertEquals(0,runtimeService.createProcessInstanceQuery().processInstanceId(processInstance.getId()).count());
  varMap=new HashMap<String,Object>();
  varMap.put("_ACTIVITI_SKIP_EXPRESSION_ENABLED",true);
  varMap.put("input",3);
  processInstance=runtimeService.startProcessInstanceByKey("inclusiveGwSkipExpression",varMap);
  assertTrue(processInstance.isEnded());
}
