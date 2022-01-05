@Deployment public void testGetVariablesByExecutionIds(){
  ProcessInstance processInstance1=runtimeService.startProcessInstanceByKey("oneTaskProcess");
  ProcessInstance processInstance2=runtimeService.startProcessInstanceByKey("oneTaskProcess");
  Task task1=taskService.createTaskQuery().processInstanceId(processInstance1.getId()).singleResult();
  Task task2=taskService.createTaskQuery().processInstanceId(processInstance2.getId()).singleResult();
  taskService.setVariableLocal(task1.getId(),"taskVar1","sayHello1");
  taskService.setVariable(task1.getId(),"executionVar1","helloWorld1");
  taskService.setVariableLocal(task2.getId(),"taskVar2","sayHello2");
  taskService.setVariable(task2.getId(),"executionVar2","helloWorld2");
  Set<String> executionIds=new HashSet<String>();
  executionIds.add(processInstance1.getId());
  List<VariableInstance> variables=runtimeService.getVariableInstancesByExecutionIds(executionIds);
  assertEquals(1,variables.size());
  checkVariable(processInstance1.getId(),"executionVar1","helloWorld1",variables);
  executionIds=new HashSet<String>();
  executionIds.add(processInstance1.getId());
  executionIds.add(processInstance2.getId());
  variables=runtimeService.getVariableInstancesByExecutionIds(executionIds);
  assertEquals(2,variables.size());
  checkVariable(processInstance1.getId(),"executionVar1","helloWorld1",variables);
  checkVariable(processInstance2.getId(),"executionVar2","helloWorld2",variables);
}
