@Deployment public void testMultipleCandidateUsers(){
  runtimeService.startProcessInstanceByKey("multipleCandidateUsersExample",Collections.singletonMap("Variable",(Object)"var"));
  assertEquals(1,taskService.createTaskQuery().taskCandidateUser(GONZO,GONZOSGROUPS).list().size());
  assertEquals(1,taskService.createTaskQuery().taskCandidateUser(KERMIT,KERMITSGROUPS).list().size());
  List<Task> tasks=taskService.createTaskQuery().taskInvolvedUser(KERMIT).list();
  assertEquals(1,tasks.size());
  Task task=tasks.get(0);
  taskService.setVariableLocal(task.getId(),"taskVar",123);
  tasks=taskService.createTaskQuery().taskInvolvedUser(KERMIT).includeProcessVariables().includeTaskLocalVariables().list();
  task=tasks.get(0);
  assertEquals(1,task.getProcessVariables().size());
  assertEquals(1,task.getTaskLocalVariables().size());
  taskService.addUserIdentityLink(task.getId(),GONZO,"test");
  tasks=taskService.createTaskQuery().taskInvolvedUser(GONZO).includeProcessVariables().includeTaskLocalVariables().list();
  assertEquals(1,tasks.size());
  assertEquals(1,task.getProcessVariables().size());
  assertEquals(1,task.getTaskLocalVariables().size());
}
