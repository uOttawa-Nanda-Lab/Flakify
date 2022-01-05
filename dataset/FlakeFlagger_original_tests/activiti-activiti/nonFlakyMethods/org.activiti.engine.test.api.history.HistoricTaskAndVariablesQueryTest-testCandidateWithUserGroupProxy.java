@Deployment public void testCandidateWithUserGroupProxy(){
  Mockito.when(userGroupManager.getUserGroups(KERMIT)).thenReturn(KERMITSGROUPS);
  Mockito.when(userGroupManager.getUserGroups(GONZO)).thenReturn(GONZOSGROUPS);
  Mockito.when(userGroupManager.getUserGroups(FOZZIE)).thenReturn(FOZZIESGROUPS);
  if (processEngineConfiguration.getHistoryLevel().isAtLeast(HistoryLevel.ACTIVITY)) {
    runtimeService.startProcessInstanceByKey("oneTaskProcess");
    List<HistoricTaskInstance> tasks=historyService.createHistoricTaskInstanceQuery().taskCandidateUser(KERMIT,KERMITSGROUPS).list();
    assertEquals(3,tasks.size());
    tasks=historyService.createHistoricTaskInstanceQuery().taskCandidateUser(GONZO).list();
    assertEquals(0,tasks.size());
    tasks=historyService.createHistoricTaskInstanceQuery().taskCandidateUser(FOZZIE).list();
    assertEquals(1,tasks.size());
    List<String> groups=new ArrayList<String>();
    groups.add("management");
    groups.add("accountancy");
    tasks=historyService.createHistoricTaskInstanceQuery().taskCandidateUser(KERMIT).taskCandidateGroupIn(groups).list();
    assertEquals(3,tasks.size());
    tasks=historyService.createHistoricTaskInstanceQuery().taskCandidateUser(GONZO).taskCandidateGroupIn(groups).list();
    assertEquals(1,tasks.size());
    tasks=historyService.createHistoricTaskInstanceQuery().taskCandidateUser(KERMIT).list();
    assertEquals(3,tasks.size());
    tasks=historyService.createHistoricTaskInstanceQuery().taskCandidateUser(GONZO).list();
    assertEquals(0,tasks.size());
    tasks=historyService.createHistoricTaskInstanceQuery().taskCandidateUser(FOZZIE).list();
    assertEquals(1,tasks.size());
    tasks=historyService.createHistoricTaskInstanceQuery().taskCandidateUser(KERMIT).taskCandidateGroupIn(Arrays.asList("management")).list();
    assertEquals(3,tasks.size());
    tasks=historyService.createHistoricTaskInstanceQuery().taskCandidateUser(GONZO).taskCandidateGroupIn(Arrays.asList("management")).list();
    assertEquals(1,tasks.size());
    tasks=historyService.createHistoricTaskInstanceQuery().taskCandidateUser(GONZO).taskCandidateGroupIn(Arrays.asList("invalid")).list();
    assertEquals(0,tasks.size());
  }
}
