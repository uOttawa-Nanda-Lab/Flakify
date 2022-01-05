@Deployment(resources={"org/activiti/engine/test/history/oneTaskProcess.bpmn20.xml"}) public void testHistoricIdentityLinksOnProcessInstance(){
  if (processEngineConfiguration.getHistoryLevel().isAtLeast(HistoryLevel.AUDIT)) {
    ProcessInstance pi=runtimeService.startProcessInstanceByKey("oneTaskProcess");
    runtimeService.addUserIdentityLink(pi.getId(),"kermit","myType");
    List<HistoricIdentityLink> historicLinks=historyService.getHistoricIdentityLinksForProcessInstance(pi.getId());
    assertEquals(1,historicLinks.size());
    assertEquals("myType",historicLinks.get(0).getType());
    assertEquals("kermit",historicLinks.get(0).getUserId());
    assertNull(historicLinks.get(0).getGroupId());
    assertEquals(pi.getId(),historicLinks.get(0).getProcessInstanceId());
    taskService.complete(taskService.createTaskQuery().processInstanceId(pi.getId()).singleResult().getId());
    assertNull(runtimeService.createProcessInstanceQuery().processInstanceId(pi.getId()).singleResult());
    assertEquals(1,historyService.getHistoricIdentityLinksForProcessInstance(pi.getId()).size());
    historyService.deleteHistoricProcessInstance(pi.getId());
    assertEquals(0,historyService.getHistoricIdentityLinksForProcessInstance(pi.getId()).size());
  }
}
