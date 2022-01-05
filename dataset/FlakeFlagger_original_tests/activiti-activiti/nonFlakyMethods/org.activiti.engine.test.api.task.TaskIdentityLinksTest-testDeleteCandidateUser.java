@Deployment(resources="org/activiti/engine/test/api/task/TaskIdentityLinksTest.testDeleteCandidateUser.bpmn20.xml") public void testDeleteCandidateUser(){
  runtimeService.startProcessInstanceByKey("TaskIdentityLinks");
  String taskId=taskService.createTaskQuery().singleResult().getId();
  List<IdentityLink> identityLinks=taskService.getIdentityLinksForTask(taskId);
  assertEquals(1,identityLinks.size());
  IdentityLink identityLink=identityLinks.get(0);
  assertEquals("user",identityLink.getUserId());
}
