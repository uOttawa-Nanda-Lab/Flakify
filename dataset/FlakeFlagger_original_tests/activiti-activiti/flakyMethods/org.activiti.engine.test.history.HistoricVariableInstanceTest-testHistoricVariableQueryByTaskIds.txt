public void testHistoricVariableQueryByTaskIds(){
  deployTwoTasksTestProcess();
  String processInstanceId=runtimeService.startProcessInstanceByKey("twoTasksProcess").getId();
  List<Task> tasks=taskService.createTaskQuery().processInstanceId(processInstanceId).list();
  taskService.setVariableLocal(tasks.get(0).getId(),"taskVar1","hello1");
  taskService.setVariableLocal(tasks.get(1).getId(),"taskVar2","hello2");
  Set<String> taskIds=new HashSet<String>();
  taskIds.add(tasks.get(0).getId());
  taskIds.add(tasks.get(1).getId());
  List<HistoricVariableInstance> historicVariableInstances=historyService.createHistoricVariableInstanceQuery().taskIds(taskIds).list();
  assertEquals(2,historyService.createHistoricVariableInstanceQuery().taskIds(taskIds).count());
  assertEquals(2,historicVariableInstances.size());
  assertEquals("taskVar1",historicVariableInstances.get(0).getVariableName());
  assertEquals("hello1",historicVariableInstances.get(0).getValue());
  assertEquals("taskVar2",historicVariableInstances.get(1).getVariableName());
  assertEquals("hello2",historicVariableInstances.get(1).getValue());
  taskIds=new HashSet<String>();
  taskIds.add(tasks.get(0).getId());
  historicVariableInstances=historyService.createHistoricVariableInstanceQuery().taskIds(taskIds).list();
  assertEquals(1,historyService.createHistoricVariableInstanceQuery().taskIds(taskIds).count());
  assertEquals(1,historicVariableInstances.size());
  assertEquals("taskVar1",historicVariableInstances.get(0).getVariableName());
  assertEquals("hello1",historicVariableInstances.get(0).getValue());
}
