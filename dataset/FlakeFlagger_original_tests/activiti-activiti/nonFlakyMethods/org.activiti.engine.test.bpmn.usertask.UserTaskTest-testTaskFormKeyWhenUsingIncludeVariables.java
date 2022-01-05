public void testTaskFormKeyWhenUsingIncludeVariables(){
  deployOneTaskTestProcess();
  runtimeService.startProcessInstanceByKey("oneTaskProcess");
  Task task=taskService.createTaskQuery().singleResult();
  assertNotNull(task);
  Map<String,Object> vars=new HashMap<String,Object>();
  for (int i=0; i < 20; i++) {
    vars.put("var" + i,i * 2);
  }
  taskService.setVariables(task.getId(),vars);
  task=taskService.createTaskQuery().singleResult();
  task.setFormKey("test123");
  taskService.saveTask(task);
  task=taskService.createTaskQuery().includeProcessVariables().singleResult();
  assertEquals(vars.size(),task.getProcessVariables().size());
  assertEquals("test123",task.getFormKey());
}
