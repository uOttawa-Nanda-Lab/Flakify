public void testHistoricVariableOkOnUpdate(){
  if (processEngineConfiguration.getHistoryLevel().isAtLeast(HistoryLevel.AUDIT)) {
    Task task=taskService.newTask();
    task.setName("test execution");
    task.setOwner("josOwner");
    task.setAssignee("JosAssignee");
    taskService.saveTask(task);
    Map<String,Object> taskVariables=new HashMap<String,Object>();
    taskVariables.put("finishedAmount",0);
    taskService.setVariables(task.getId(),taskVariables);
    Map<String,Object> finishVariables=new HashMap<String,Object>();
    finishVariables.put("finishedAmount",40);
    taskService.complete(task.getId(),finishVariables);
    List<HistoricVariableInstance> hisVarList=historyService.createHistoricVariableInstanceQuery().taskId(task.getId()).list();
    assertEquals(1,hisVarList.size());
    assertEquals(40,hisVarList.get(0).getValue());
    historyService.deleteHistoricTaskInstance(task.getId());
  }
}
