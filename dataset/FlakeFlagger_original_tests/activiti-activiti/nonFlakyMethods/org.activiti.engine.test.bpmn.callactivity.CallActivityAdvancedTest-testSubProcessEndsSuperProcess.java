/** 
 * Test case for a possible tricky case: reaching the end event of the subprocess leads to an end event in the super process instance.
 */
@Deployment(resources={"org/activiti/engine/test/bpmn/callactivity/CallActivity.testSubProcessEndsSuperProcess.bpmn20.xml","org/activiti/engine/test/bpmn/callactivity/simpleSubProcess.bpmn20.xml"}) public void testSubProcessEndsSuperProcess(){
  ProcessInstance processInstance=runtimeService.startProcessInstanceByKey("subProcessEndsSuperProcess");
  TaskQuery taskQuery=taskService.createTaskQuery();
  Task taskBeforeSubProcess=taskQuery.singleResult();
  assertEquals("Task in subprocess",taskBeforeSubProcess.getName());
  taskService.complete(taskBeforeSubProcess.getId());
  assertProcessEnded(processInstance.getId());
  assertEquals(0,runtimeService.createExecutionQuery().list().size());
}
