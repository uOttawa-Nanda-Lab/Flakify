public void testTaskLocalAndProcessInstanceVariableEqualsInOr(){
  deployOneTaskTestProcess();
  for (int i=0; i < 10; i++) {
    runtimeService.startProcessInstanceByKey("oneTaskProcess");
  }
  List<Task> allTasks=taskService.createTaskQuery().processDefinitionKey("oneTaskProcess").list();
  assertEquals(10,allTasks.size());
  taskService.setVariableLocal(allTasks.get(0).getId(),"localVar","someValue");
  taskService.setVariableLocal(allTasks.get(1).getId(),"localVar","someValue");
  runtimeService.setVariable(allTasks.get(2).getProcessInstanceId(),"var","theValue");
  runtimeService.setVariable(allTasks.get(3).getProcessInstanceId(),"var","theValue");
  runtimeService.setVariable(allTasks.get(4).getProcessInstanceId(),"var","theValue");
  assertEquals(2,taskService.createTaskQuery().taskVariableValueEquals("localVar","someValue").list().size());
  assertEquals(3,taskService.createTaskQuery().processVariableValueEquals("var","theValue").list().size());
  assertEquals(5,taskService.createTaskQuery().or().taskVariableValueEquals("localVar","someValue").processVariableValueEquals("var","theValue").endOr().list().size());
  assertEquals(5,taskService.createTaskQuery().or().taskVariableValueEquals("localVar","someValue").processVariableValueEquals("var","theValue").endOr().or().processDefinitionKey("oneTaskProcess").processDefinitionId("notexisting").endOr().list().size());
}
