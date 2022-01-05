@Deployment public void testDirectSequenceFlow(){
  Map<String,Object> varMap=new HashMap<String,Object>();
  varMap.put("input",1);
  ProcessInstance processInstance=runtimeService.startProcessInstanceByKey("inclusiveGwDirectSequenceFlow",varMap);
  Task task=taskService.createTaskQuery().singleResult();
  assertNotNull(task);
  assertEquals("theTask1",task.getTaskDefinitionKey());
  taskService.complete(task.getId());
  assertEquals(0,runtimeService.createProcessInstanceQuery().processInstanceId(processInstance.getId()).count());
  varMap=new HashMap<String,Object>();
  varMap.put("input",3);
  processInstance=runtimeService.startProcessInstanceByKey("inclusiveGwDirectSequenceFlow",varMap);
  List<Task> tasks=taskService.createTaskQuery().list();
  assertEquals(2,tasks.size());
  taskService.complete(tasks.get(0).getId());
  taskService.complete(tasks.get(1).getId());
  assertEquals(0,runtimeService.createProcessInstanceQuery().processInstanceId(processInstance.getId()).count());
  varMap=new HashMap<String,Object>();
  varMap.put("input",0);
  processInstance=runtimeService.startProcessInstanceByKey("inclusiveGwDirectSequenceFlow",varMap);
  assertTrue(processInstance.isEnded());
}
