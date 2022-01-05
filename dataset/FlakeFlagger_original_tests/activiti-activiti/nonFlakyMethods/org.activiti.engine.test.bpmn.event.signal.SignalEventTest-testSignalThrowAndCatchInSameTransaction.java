/** 
 * From https://forums.activiti.org/content/boundary-signal-causes-already-taking-transition
 */
@Deployment public void testSignalThrowAndCatchInSameTransaction(){
  String fileExistsVar="fileexists";
  FileExistsMock.getInstance().removeFile();
  ProcessInstance firstProcessInstance=runtimeService.startProcessInstanceByKey("signalBoundaryProcess");
  assertNotNull(firstProcessInstance);
  Task firstTask=taskService.createTaskQuery().singleResult();
  assertEquals("Add a file",firstTask.getName());
  Map<String,Object> vars=runtimeService.getVariables(firstTask.getExecutionId());
  assertEquals(false,vars.get(fileExistsVar));
  ProcessInstance secondProcessInstance=runtimeService.startProcessInstanceByKey("signalBoundaryProcess");
  assertNotNull(secondProcessInstance);
  List<Task> tasks=taskService.createTaskQuery().list();
  assertEquals(2,tasks.size());
  Task secondTask=taskService.createTaskQuery().processInstanceId(secondProcessInstance.getProcessInstanceId()).singleResult();
  assertEquals("Add a file",secondTask.getName());
  vars=runtimeService.getVariables(secondTask.getExecutionId());
  assertEquals(false,vars.get(fileExistsVar));
  taskService.claim(firstTask.getId(),"user");
  FileExistsMock.getInstance().touchFile();
  taskService.complete(firstTask.getId());
  List<Task> usingTask=taskService.createTaskQuery().taskName("Use the file").list();
  assertEquals(1,usingTask.size());
}
