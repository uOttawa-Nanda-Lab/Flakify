@Deployment(resources={"org/activiti/engine/test/api/twoTasksProcess.bpmn20.xml"}) public void testCompleteWithParametersTask(){
  ProcessInstance processInstance=runtimeService.startProcessInstanceByKey("twoTasksProcess");
  Task task=taskService.createTaskQuery().singleResult();
  assertEquals("First task",task.getName());
  Map<String,Object> taskParams=new HashMap<String,Object>();
  taskParams.put("myParam","myValue");
  taskService.complete(task.getId(),taskParams);
  task=taskService.createTaskQuery().singleResult();
  assertEquals("Second task",task.getName());
  Map<String,Object> variables=runtimeService.getVariables(processInstance.getId());
  assertEquals(1,variables.size());
  assertEquals("myValue",variables.get("myParam"));
}
