@Deployment(resources={"org/activiti/engine/test/api/oneTaskProcess.bpmn20.xml"}) public void testTaskOperationsFailAfterProcessInstanceSuspend(){
  ProcessDefinition processDefinition=repositoryService.createProcessDefinitionQuery().singleResult();
  ProcessInstance processInstance=runtimeService.startProcessInstanceById(processDefinition.getId());
  final Task task=taskService.createTaskQuery().processInstanceId(processInstance.getId()).singleResult();
  assertNotNull(task);
  runtimeService.suspendProcessInstanceById(processInstance.getId());
  try {
    taskService.complete(task.getId());
    fail("It is not allowed to complete a task of a suspended process instance");
  }
 catch (  ActivitiException e) {
  }
  try {
    taskService.claim(task.getId(),"jos");
    fail("It is not allowed to claim a task of a suspended process instance");
  }
 catch (  ActivitiException e) {
  }
  try {
    taskService.setVariable(task.getId(),"someVar","someValue");
    fail("It is not allowed to set a variable on a task of a suspended process instance");
  }
 catch (  ActivitiException e) {
  }
  try {
    taskService.setVariableLocal(task.getId(),"someVar","someValue");
    fail("It is not allowed to set a variable on a task of a suspended process instance");
  }
 catch (  ActivitiException e) {
  }
  try {
    HashMap<String,String> variables=new HashMap<String,String>();
    variables.put("varOne","one");
    variables.put("varTwo","two");
    taskService.setVariables(task.getId(),variables);
    fail("It is not allowed to set variables on a task of a suspended process instance");
  }
 catch (  ActivitiException e) {
  }
  try {
    HashMap<String,String> variables=new HashMap<String,String>();
    variables.put("varOne","one");
    variables.put("varTwo","two");
    taskService.setVariablesLocal(task.getId(),variables);
    fail("It is not allowed to set variables on a task of a suspended process instance");
  }
 catch (  ActivitiException e) {
  }
  try {
    taskService.removeVariable(task.getId(),"someVar");
    fail("It is not allowed to remove a variable on a task of a suspended process instance");
  }
 catch (  ActivitiException e) {
  }
  try {
    taskService.removeVariableLocal(task.getId(),"someVar");
    fail("It is not allowed to remove a variable on a task of a suspended process instance");
  }
 catch (  ActivitiException e) {
  }
  try {
    taskService.removeVariables(task.getId(),Arrays.asList("one","two"));
    fail("It is not allowed to remove variables on a task of a suspended process instance");
  }
 catch (  ActivitiException e) {
  }
  try {
    taskService.removeVariablesLocal(task.getId(),Arrays.asList("one","two"));
    fail("It is not allowed to remove variables on a task of a suspended process instance");
  }
 catch (  ActivitiException e) {
  }
  try {
    taskService.addCandidateGroup(task.getId(),"blahGroup");
    fail("It is not allowed to add a candidate group on a task of a suspended process instance");
  }
 catch (  ActivitiException e) {
  }
  try {
    taskService.addCandidateUser(task.getId(),"blahUser");
    fail("It is not allowed to add a candidate user on a task of a suspended process instance");
  }
 catch (  ActivitiException e) {
  }
  try {
    taskService.addGroupIdentityLink(task.getId(),"blahGroup",IdentityLinkType.CANDIDATE);
    fail("It is not allowed to add a candidate user on a task of a suspended process instance");
  }
 catch (  ActivitiException e) {
  }
  try {
    taskService.addUserIdentityLink(task.getId(),"blahUser",IdentityLinkType.OWNER);
    fail("It is not allowed to add an identityLink on a task of a suspended process instance");
  }
 catch (  ActivitiException e) {
  }
  try {
    taskService.addComment(task.getId(),processInstance.getId(),"test comment");
    fail("It is not allowed to add a comment on a task of a suspended process instance");
  }
 catch (  ActivitiException e) {
  }
  try {
    taskService.createAttachment("text",task.getId(),processInstance.getId(),"testName","testDescription","http://test.com");
    fail("It is not allowed to add an attachment on a task of a suspended process instance");
  }
 catch (  ActivitiException e) {
  }
  try {
    taskService.setAssignee(task.getId(),"mispiggy");
    fail("It is not allowed to set an assignee on a task of a suspended process instance");
  }
 catch (  ActivitiException e) {
  }
  try {
    taskService.setOwner(task.getId(),"kermit");
    fail("It is not allowed to set an owner on a task of a suspended process instance");
  }
 catch (  ActivitiException e) {
  }
  try {
    taskService.setPriority(task.getId(),99);
    fail("It is not allowed to set a priority on a task of a suspended process instance");
  }
 catch (  ActivitiException e) {
  }
}
