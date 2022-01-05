public void testHistoricVariableQuery2(){
  deployTwoTasksTestProcess();
  Map<String,Object> startVars=new HashMap<String,Object>();
  startVars.put("startVar","hello");
  String processInstanceId=runtimeService.startProcessInstanceByKey("twoTasksProcess",startVars).getId();
  List<Task> tasks=taskService.createTaskQuery().processInstanceId(processInstanceId).list();
  for (int i=0; i < tasks.size(); i++) {
    runtimeService.setVariableLocal(tasks.get(i).getExecutionId(),"executionVar" + i,i);
    taskService.setVariableLocal(tasks.get(i).getId(),"taskVar" + i,i);
  }
  List<HistoricVariableInstance> historicVariableInstances=historyService.createHistoricVariableInstanceQuery().processInstanceId(processInstanceId).orderByVariableName().asc().list();
  assertEquals(5,historicVariableInstances.size());
  List<String> expectedVariableNames=Arrays.asList("executionVar0","executionVar1","startVar","taskVar0","taskVar1");
  for (int i=0; i < expectedVariableNames.size(); i++) {
    assertEquals(expectedVariableNames.get(i),historicVariableInstances.get(i).getVariableName());
  }
  historicVariableInstances=historyService.createHistoricVariableInstanceQuery().executionId(tasks.get(0).getExecutionId()).orderByVariableName().asc().list();
  assertEquals(2,historicVariableInstances.size());
  assertEquals("executionVar0",historicVariableInstances.get(0).getVariableName());
  assertEquals("taskVar0",historicVariableInstances.get(1).getVariableName());
  historicVariableInstances=historyService.createHistoricVariableInstanceQuery().executionId(tasks.get(1).getExecutionId()).orderByVariableName().asc().list();
  assertEquals(2,historicVariableInstances.size());
  assertEquals("executionVar1",historicVariableInstances.get(0).getVariableName());
  assertEquals("taskVar1",historicVariableInstances.get(1).getVariableName());
  historicVariableInstances=historyService.createHistoricVariableInstanceQuery().processInstanceId(processInstanceId).executionId(tasks.get(0).getExecutionId()).orderByVariableName().asc().list();
  assertEquals(2,historicVariableInstances.size());
  assertEquals("executionVar0",historicVariableInstances.get(0).getVariableName());
  assertEquals("taskVar0",historicVariableInstances.get(1).getVariableName());
  historicVariableInstances=historyService.createHistoricVariableInstanceQuery().processInstanceId(processInstanceId).executionId(tasks.get(1).getExecutionId()).orderByVariableName().asc().list();
  assertEquals(2,historicVariableInstances.size());
  assertEquals("executionVar1",historicVariableInstances.get(0).getVariableName());
  assertEquals("taskVar1",historicVariableInstances.get(1).getVariableName());
  historicVariableInstances=historyService.createHistoricVariableInstanceQuery().taskId(tasks.get(0).getId()).list();
  assertEquals(1,historicVariableInstances.size());
  assertEquals("taskVar0",historicVariableInstances.get(0).getVariableName());
  historicVariableInstances=historyService.createHistoricVariableInstanceQuery().taskId(tasks.get(1).getId()).list();
  assertEquals(1,historicVariableInstances.size());
  assertEquals("taskVar1",historicVariableInstances.get(0).getVariableName());
  historicVariableInstances=historyService.createHistoricVariableInstanceQuery().processInstanceId(processInstanceId).taskId(tasks.get(0).getId()).list();
  assertEquals(1,historicVariableInstances.size());
  assertEquals("taskVar0",historicVariableInstances.get(0).getVariableName());
  historicVariableInstances=historyService.createHistoricVariableInstanceQuery().processInstanceId(processInstanceId).taskId(tasks.get(1).getId()).list();
  assertEquals(1,historicVariableInstances.size());
  assertEquals("taskVar1",historicVariableInstances.get(0).getVariableName());
}
