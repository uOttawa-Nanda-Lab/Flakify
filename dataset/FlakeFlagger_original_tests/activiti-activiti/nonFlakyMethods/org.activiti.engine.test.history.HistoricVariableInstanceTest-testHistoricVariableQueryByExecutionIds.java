public void testHistoricVariableQueryByExecutionIds(){
  deployTwoTasksTestProcess();
  Set<String> processInstanceIds=new HashSet<String>();
  Set<String> testProcessInstanceIds=new HashSet<String>();
  for (int i=0; i < 3; i++) {
    Map<String,Object> startVars=new HashMap<String,Object>();
    if (i == 1) {
      startVars.put("startVar2","hello2");
    }
 else {
      startVars.put("startVar","hello");
    }
    String processInstanceId=runtimeService.startProcessInstanceByKey("twoTasksProcess",startVars).getId();
    processInstanceIds.add(processInstanceId);
    if (i != 1) {
      testProcessInstanceIds.add(processInstanceId);
    }
  }
  assertThat(historyService.createHistoricVariableInstanceQuery().executionIds(testProcessInstanceIds).list().size()).isEqualTo((int)historyService.createHistoricVariableInstanceQuery().executionIds(testProcessInstanceIds).count()).isEqualTo(2);
  List<HistoricVariableInstance> historicVariableInstances=historyService.createHistoricVariableInstanceQuery().executionIds(testProcessInstanceIds).list();
  assertThat(historicVariableInstances).hasSize(2).extracting("name","value").containsExactly(tuple("startVar","hello"),tuple("startVar","hello"));
  historicVariableInstances=historyService.createHistoricVariableInstanceQuery().executionIds(processInstanceIds).list();
  assertThat(historicVariableInstances).hasSize(3).extracting("name","value").containsExactlyInAnyOrder(tuple("startVar","hello"),tuple("startVar","hello"),tuple("startVar2","hello2"));
}
