@Deployment public void testGetVariablesLocalByTaskIds(){
  ProcessInstance processInstance1=runtimeService.startProcessInstanceByKey("twoTaskProcess");
  ProcessInstance processInstance2=runtimeService.startProcessInstanceByKey("twoTaskProcess");
  List<Task> taskList1=taskService.createTaskQuery().processInstanceId(processInstance1.getId()).list();
  List<Task> taskList2=taskService.createTaskQuery().processInstanceId(processInstance2.getId()).list();
  for (  Task task : taskList1) {
    if ("usertask1".equals(task.getTaskDefinitionKey())) {
      taskService.setVariableLocal(task.getId(),"taskVar1","sayHello1");
    }
 else {
      taskService.setVariableLocal(task.getId(),"taskVar2","sayHello2");
    }
    taskService.setVariable(task.getId(),"executionVar1","helloWorld1");
  }
  for (  Task task : taskList2) {
    if ("usertask1".equals(task.getTaskDefinitionKey())) {
      taskService.setVariableLocal(task.getId(),"taskVar3","sayHello3");
    }
 else {
      taskService.setVariableLocal(task.getId(),"taskVar4","sayHello4");
    }
    taskService.setVariable(task.getId(),"executionVar2","helloWorld2");
  }
  Set<String> taskIds=new HashSet<String>();
  taskIds.add(taskList1.get(0).getId());
  taskIds.add(taskList1.get(1).getId());
  List<VariableInstance> variables=taskService.getVariableInstancesLocalByTaskIds(taskIds);
  assertEquals(2,variables.size());
  checkVariable(taskList1.get(0).getId(),"taskVar1","sayHello1",variables);
  checkVariable(taskList1.get(1).getId(),"taskVar2","sayHello2",variables);
  taskIds=new HashSet<String>();
  taskIds.add(taskList1.get(0).getId());
  taskIds.add(taskList1.get(1).getId());
  taskIds.add(taskList2.get(0).getId());
  taskIds.add(taskList2.get(1).getId());
  variables=taskService.getVariableInstancesLocalByTaskIds(taskIds);
  assertEquals(4,variables.size());
  checkVariable(taskList1.get(0).getId(),"taskVar1","sayHello1",variables);
  checkVariable(taskList1.get(1).getId(),"taskVar2","sayHello2",variables);
  checkVariable(taskList2.get(0).getId(),"taskVar3","sayHello3",variables);
  checkVariable(taskList2.get(1).getId(),"taskVar4","sayHello4",variables);
  taskIds=new HashSet<String>();
  taskIds.add(taskList1.get(0).getId());
  taskIds.add(taskList2.get(1).getId());
  variables=taskService.getVariableInstancesLocalByTaskIds(taskIds);
  assertEquals(2,variables.size());
  checkVariable(taskList1.get(0).getId(),"taskVar1","sayHello1",variables);
  checkVariable(taskList2.get(1).getId(),"taskVar4","sayHello4",variables);
}
