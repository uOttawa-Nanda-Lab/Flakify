@Deployment(resources={"org/activiti/engine/test/bpmn/usertask/DynamicUserTaskTest.basictask.bpmn20.xml"}) public void testChangeCandidateUsers(){
  ProcessInstance processInstance=runtimeService.startProcessInstanceByKey("dynamicUserTask");
  String processDefinitionId=processInstance.getProcessDefinitionId();
  Task task=taskService.createTaskQuery().processInstanceId(processInstance.getId()).singleResult();
  List<IdentityLink> taskIdentityLinks=taskService.getIdentityLinksForTask(task.getId());
  boolean candidateUserTestFound=false;
  for (  IdentityLink identityLink : taskIdentityLinks) {
    if (IdentityLinkType.CANDIDATE.equals(identityLink.getType()) && identityLink.getUserId() != null && identityLink.getGroupId() == null) {
      if ("test".equals(identityLink.getUserId())) {
        candidateUserTestFound=true;
      }
    }
  }
  assertFalse(candidateUserTestFound);
  taskService.complete(task.getId());
  assertProcessEnded(processInstance.getId());
  ObjectNode infoNode=dynamicBpmnService.changeUserTaskCandidateUser("task1","test",true);
  dynamicBpmnService.saveProcessDefinitionInfo(processDefinitionId,infoNode);
  processInstance=runtimeService.startProcessInstanceByKey("dynamicUserTask");
  task=taskService.createTaskQuery().processInstanceId(processInstance.getId()).singleResult();
  taskIdentityLinks=taskService.getIdentityLinksForTask(task.getId());
  candidateUserTestFound=false;
  for (  IdentityLink identityLink : taskIdentityLinks) {
    if (IdentityLinkType.CANDIDATE.equals(identityLink.getType()) && identityLink.getUserId() != null && identityLink.getGroupId() == null) {
      if ("test".equals(identityLink.getUserId())) {
        candidateUserTestFound=true;
      }
    }
  }
  assertTrue(candidateUserTestFound);
  taskService.complete(task.getId());
  infoNode=dynamicBpmnService.getProcessDefinitionInfo(processDefinitionId);
  dynamicBpmnService.changeUserTaskCandidateUser("task1","test2",false,infoNode);
  dynamicBpmnService.saveProcessDefinitionInfo(processDefinitionId,infoNode);
  processInstance=runtimeService.startProcessInstanceByKey("dynamicUserTask");
  task=taskService.createTaskQuery().processInstanceId(processInstance.getId()).singleResult();
  taskIdentityLinks=taskService.getIdentityLinksForTask(task.getId());
  candidateUserTestFound=false;
  boolean candidateUserTest2Found=false;
  for (  IdentityLink identityLink : taskIdentityLinks) {
    if (IdentityLinkType.CANDIDATE.equals(identityLink.getType()) && identityLink.getUserId() != null && identityLink.getGroupId() == null) {
      if ("test".equals(identityLink.getUserId())) {
        candidateUserTestFound=true;
      }
 else       if ("test2".equals(identityLink.getUserId())) {
        candidateUserTest2Found=true;
      }
    }
  }
  assertTrue(candidateUserTestFound);
  assertTrue(candidateUserTest2Found);
  taskService.complete(task.getId());
  assertProcessEnded(processInstance.getId());
}
