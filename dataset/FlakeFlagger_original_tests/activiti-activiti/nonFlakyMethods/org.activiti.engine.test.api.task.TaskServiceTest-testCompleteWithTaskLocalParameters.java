@Deployment public void testCompleteWithTaskLocalParameters(){
  ProcessInstance processInstance=runtimeService.startProcessInstanceByKey("testTaskLocalVars");
  Task task=taskService.createTaskQuery().singleResult();
  Map<String,Object> taskParams=new HashMap<String,Object>();
  taskParams.put("a",1);
  taskParams.put("b",1);
  taskService.complete(task.getId(),taskParams,true);
  assertNull(runtimeService.getVariable(processInstance.getId(),"a"));
  assertNull(runtimeService.getVariable(processInstance.getId(),"b"));
  assertEquals(new Integer(2),runtimeService.getVariable(processInstance.getId(),"sum"));
  taskService.createTaskQuery().singleResult();
}
