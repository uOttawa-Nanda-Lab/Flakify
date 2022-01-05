@Deployment public void testHistoricIdentityLinksOnTask() throws Exception {
  ProcessInstance processInstance=runtimeService.startProcessInstanceByKey("historicIdentityLinks");
  Task task=taskService.createTaskQuery().processInstanceId(processInstance.getId()).singleResult();
  assertNotNull(task);
  taskService.addUserIdentityLink(task.getId(),"gonzo","customUseridentityLink");
  assertEquals(4,taskService.getIdentityLinksForTask(task.getId()).size());
  List<HistoricIdentityLink> historicIdentityLinks=historyService.getHistoricIdentityLinksForTask(task.getId());
  assertEquals(4,historicIdentityLinks.size());
  boolean foundCandidateUser=false, foundCandidateGroup=false, foundAssignee=false, foundCustom=false;
  for (  HistoricIdentityLink link : historicIdentityLinks) {
    assertEquals(task.getId(),link.getTaskId());
    if (link.getGroupId() != null) {
      assertEquals("sales",link.getGroupId());
      foundCandidateGroup=true;
    }
 else {
      if (link.getType().equals("candidate")) {
        assertEquals("fozzie",link.getUserId());
        foundCandidateUser=true;
      }
 else       if (link.getType().equals("assignee")) {
        assertEquals("kermit",link.getUserId());
        foundAssignee=true;
      }
 else       if (link.getType().equals("customUseridentityLink")) {
        assertEquals("gonzo",link.getUserId());
        foundCustom=true;
      }
    }
  }
  assertTrue(foundAssignee);
  assertTrue(foundCandidateGroup);
  assertTrue(foundCandidateUser);
  assertTrue(foundCustom);
  taskService.complete(task.getId());
  assertEquals(4,historyService.getHistoricIdentityLinksForTask(task.getId()).size());
  historyService.deleteHistoricTaskInstance(task.getId());
  try {
    historyService.getHistoricIdentityLinksForTask(task.getId()).size();
    fail("Exception expected");
  }
 catch (  ActivitiObjectNotFoundException aonfe) {
    assertEquals(HistoricTaskInstance.class,aonfe.getObjectClass());
  }
}
