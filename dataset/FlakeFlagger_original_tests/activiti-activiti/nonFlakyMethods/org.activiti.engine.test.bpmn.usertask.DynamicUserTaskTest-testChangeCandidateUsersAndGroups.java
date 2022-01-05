@Deployment(resources={"org/activiti/engine/test/bpmn/usertask/DynamicUserTaskTest.basictask.bpmn20.xml"}) public void testChangeCandidateUsersAndGroups(){
  ProcessInstance processInstance=runtimeService.startProcessInstanceByKey("dynamicUserTask");
  String processDefinitionId=processInstance.getProcessDefinitionId();
  Task task=taskService.createTaskQuery().processInstanceId(processInstance.getId()).singleResult();
  List<IdentityLink> taskIdentityLinks=taskService.getIdentityLinksForTask(task.getId());
  boolean candidateUserTestFound=false;
  boolean candidateGroupTestFound=false;
  for (  IdentityLink identityLink : taskIdentityLinks) {
    if (IdentityLinkType.CANDIDATE.equals(identityLink.getType()) && identityLink.getUserId() != null && identityLink.getGroupId() == null) {
      if ("test".equals(identityLink.getUserId())) {
        candidateUserTestFound=true;
      }
    }
 else     if (IdentityLinkType.CANDIDATE.equals(identityLink.getType()) && identityLink.getGroupId() != null && identityLink.getUserId() == null) {
      if ("test".equals(identityLink.getGroupId())) {
        candidateGroupTestFound=true;
      }
    }
  }
  assertFalse(candidateUserTestFound);
  assertFalse(candidateGroupTestFound);
  taskService.complete(task.getId());
  assertProcessEnded(processInstance.getId());
  ObjectNode infoNode=dynamicBpmnService.changeUserTaskCandidateGroup("task1","test",true);
  dynamicBpmnService.changeUserTaskCandidateUser("task1","test",true,infoNode);
  dynamicBpmnService.saveProcessDefinitionInfo(processDefinitionId,infoNode);
  processInstance=runtimeService.startProcessInstanceByKey("dynamicUserTask");
  task=taskService.createTaskQuery().processInstanceId(processInstance.getId()).singleResult();
  taskIdentityLinks=taskService.getIdentityLinksForTask(task.getId());
  candidateUserTestFound=false;
  candidateGroupTestFound=false;
  for (  IdentityLink identityLink : taskIdentityLinks) {
    if (IdentityLinkType.CANDIDATE.equals(identityLink.getType()) && identityLink.getUserId() != null && identityLink.getGroupId() == null) {
      if ("test".equals(identityLink.getUserId())) {
        candidateUserTestFound=true;
      }
    }
 else     if (IdentityLinkType.CANDIDATE.equals(identityLink.getType()) && identityLink.getGroupId() != null && identityLink.getUserId() == null) {
      if ("test".equals(identityLink.getGroupId())) {
        candidateGroupTestFound=true;
      }
    }
  }
  assertTrue(candidateUserTestFound);
  assertTrue(candidateGroupTestFound);
  taskService.complete(task.getId());
  infoNode=dynamicBpmnService.getProcessDefinitionInfo(processDefinitionId);
  dynamicBpmnService.changeUserTaskCandidateGroup("task1","test2",false,infoNode);
  dynamicBpmnService.changeUserTaskCandidateUser("task1","test2",false,infoNode);
  dynamicBpmnService.saveProcessDefinitionInfo(processDefinitionId,infoNode);
  processInstance=runtimeService.startProcessInstanceByKey("dynamicUserTask");
  task=taskService.createTaskQuery().processInstanceId(processInstance.getId()).singleResult();
  taskIdentityLinks=taskService.getIdentityLinksForTask(task.getId());
  candidateUserTestFound=false;
  boolean candidateUserTestFound2=false;
  candidateGroupTestFound=false;
  boolean candidateGroupTest2Found=false;
  for (  IdentityLink identityLink : taskIdentityLinks) {
    if (IdentityLinkType.CANDIDATE.equals(identityLink.getType()) && identityLink.getUserId() != null && identityLink.getGroupId() == null) {
      if ("test".equals(identityLink.getUserId())) {
        candidateUserTestFound=true;
      }
 else       if ("test2".equals(identityLink.getUserId())) {
        candidateUserTestFound2=true;
      }
    }
 else     if (IdentityLinkType.CANDIDATE.equals(identityLink.getType()) && identityLink.getGroupId() != null && identityLink.getUserId() == null) {
      if ("test".equals(identityLink.getGroupId())) {
        candidateGroupTestFound=true;
      }
 else       if ("test2".equals(identityLink.getGroupId())) {
        candidateGroupTest2Found=true;
      }
    }
  }
  assertTrue(candidateUserTestFound);
  assertTrue(candidateUserTestFound2);
  assertTrue(candidateGroupTestFound);
  assertTrue(candidateGroupTest2Found);
  taskService.complete(task.getId());
  assertProcessEnded(processInstance.getId());
}
