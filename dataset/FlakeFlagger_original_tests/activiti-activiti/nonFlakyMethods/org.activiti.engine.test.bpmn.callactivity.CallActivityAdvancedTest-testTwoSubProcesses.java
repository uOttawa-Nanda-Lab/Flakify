/** 
 * Test case for deleting a sub process
 */
@Deployment(resources={"org/activiti/engine/test/bpmn/callactivity/CallActivity.testTwoSubProcesses.bpmn20.xml","org/activiti/engine/test/bpmn/callactivity/simpleSubProcess.bpmn20.xml"}) public void testTwoSubProcesses(){
  ProcessInstance processInstance=runtimeService.startProcessInstanceByKey("callTwoSubProcesses");
  List<ProcessInstance> instanceList=runtimeService.createProcessInstanceQuery().list();
  assertNotNull(instanceList);
  assertEquals(3,instanceList.size());
  List<Task> taskList=taskService.createTaskQuery().list();
  assertNotNull(taskList);
  assertEquals(2,taskList.size());
  runtimeService.deleteProcessInstance(processInstance.getId(),"Test cascading");
  instanceList=runtimeService.createProcessInstanceQuery().list();
  assertNotNull(instanceList);
  assertEquals(0,instanceList.size());
  taskList=taskService.createTaskQuery().list();
  assertNotNull(taskList);
  assertEquals(0,taskList.size());
}
