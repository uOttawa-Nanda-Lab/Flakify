@Deployment public void testCustomIdentityLink(){
  runtimeService.startProcessInstanceByKey("customIdentityLink");
  List<Task> tasks=taskService.createTaskQuery().taskInvolvedUser("kermit").list();
  assertEquals(1,tasks.size());
  List<IdentityLink> identityLinks=taskService.getIdentityLinksForTask(tasks.get(0).getId());
  assertEquals(2,identityLinks.size());
  for (  IdentityLink idLink : identityLinks) {
    assertEquals("businessAdministrator",idLink.getType());
    String userId=idLink.getUserId();
    if (userId == null) {
      assertEquals("management",idLink.getGroupId());
    }
 else {
      assertEquals("kermit",userId);
    }
  }
}
