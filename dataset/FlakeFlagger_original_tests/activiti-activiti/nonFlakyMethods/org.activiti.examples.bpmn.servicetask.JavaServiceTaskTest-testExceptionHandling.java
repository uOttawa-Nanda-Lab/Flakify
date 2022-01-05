@Deployment public void testExceptionHandling(){
  Map<String,Object> vars=new HashMap<String,Object>();
  vars.put("var","no-exception");
  runtimeService.startProcessInstanceByKey("exceptionHandling",vars);
  assertEquals(0,runtimeService.createProcessInstanceQuery().count());
  vars.put("var","throw-exception");
  runtimeService.startProcessInstanceByKey("exceptionHandling",vars);
  Task task=taskService.createTaskQuery().singleResult();
  assertEquals("Fix Exception",task.getName());
}
