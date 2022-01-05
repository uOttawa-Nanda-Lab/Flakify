@Deployment public void testCandidate(){
  if (processEngineConfiguration.getHistoryLevel().isAtLeast(HistoryLevel.ACTIVITY)) {
    ProcessInstance processInstance=runtimeService.startProcessInstanceByKey("oneTaskProcess");
    List<HistoricTaskInstance> tasks=historyService.createHistoricTaskInstanceQuery().taskCandidateUser(KERMIT,KERMITSGROUPS).list();
    assertEquals(3,tasks.size());
    tasks=historyService.createHistoricTaskInstanceQuery().taskCandidateUser(GONZO,GONZOSGROUPS).list();
    assertEquals(0,tasks.size());
    tasks=historyService.createHistoricTaskInstanceQuery().taskCandidateUser(FOZZIE,FOZZIESGROUPS).list();
    assertEquals(1,tasks.size());
    tasks=historyService.createHistoricTaskInstanceQuery().taskCandidateGroup("management").list();
    assertEquals(1,tasks.size());
    List<String> groups=new ArrayList<String>();
    groups.add("management");
    groups.add("accountancy");
    tasks=historyService.createHistoricTaskInstanceQuery().taskCandidateGroupIn(groups).list();
    assertEquals(1,tasks.size());
    tasks=historyService.createHistoricTaskInstanceQuery().taskCandidateUser(KERMIT,KERMITSGROUPS).taskCandidateGroupIn(groups).list();
    assertEquals(3,tasks.size());
    tasks=historyService.createHistoricTaskInstanceQuery().taskCandidateUser(GONZO,GONZOSGROUPS).taskCandidateGroupIn(groups).list();
    assertEquals(1,tasks.size());
    Task task=taskService.createTaskQuery().processInstanceId(processInstance.getId()).singleResult();
    taskService.complete(task.getId());
    assertEquals(0,taskService.createTaskQuery().processInstanceId(processInstance.getId()).count());
    tasks=historyService.createHistoricTaskInstanceQuery().taskCandidateUser(KERMIT,KERMITSGROUPS).list();
    assertEquals(3,tasks.size());
    tasks=historyService.createHistoricTaskInstanceQuery().taskCandidateUser(GONZO,GONZOSGROUPS).list();
    assertEquals(0,tasks.size());
    tasks=historyService.createHistoricTaskInstanceQuery().taskCandidateUser(FOZZIE,FOZZIESGROUPS).list();
    assertEquals(1,tasks.size());
    tasks=historyService.createHistoricTaskInstanceQuery().taskCandidateGroup("management").list();
    assertEquals(1,tasks.size());
    tasks=historyService.createHistoricTaskInstanceQuery().taskCandidateUser(KERMIT,KERMITSGROUPS).taskCandidateGroupIn(Arrays.asList("management")).list();
    assertEquals(3,tasks.size());
    tasks=historyService.createHistoricTaskInstanceQuery().taskCandidateUser(GONZO,GONZOSGROUPS).taskCandidateGroupIn(Arrays.asList("management")).list();
    assertEquals(1,tasks.size());
    tasks=historyService.createHistoricTaskInstanceQuery().taskCandidateUser(GONZO,GONZOSGROUPS).taskCandidateGroupIn(Arrays.asList("invalid")).list();
    assertEquals(0,tasks.size());
    tasks=historyService.createHistoricTaskInstanceQuery().taskCandidateGroupIn(groups).list();
    assertEquals(1,tasks.size());
  }
}
