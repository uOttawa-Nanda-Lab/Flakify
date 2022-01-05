/** 
 * Test case for handing over process variables to a sub process
 */
@Deployment(resources={"org/activiti/engine/test/bpmn/callactivity/CallActivity.testSubProcessDataInputOutput.bpmn20.xml","org/activiti/engine/test/bpmn/callactivity/simpleSubProcess.bpmn20.xml"}) public void testSubProcessWithDataInputOutput(){
  Map<String,Object> vars=new HashMap<String,Object>();
  vars.put("superVariable","Hello from the super process.");
  ProcessInstance processInstance=runtimeService.startProcessInstanceByKey("subProcessDataInputOutput",vars);
  TaskQuery taskQuery=taskService.createTaskQuery();
  Task taskBeforeSubProcess=taskQuery.singleResult();
  assertEquals("Task in subprocess",taskBeforeSubProcess.getName());
  assertEquals("Hello from the super process.",runtimeService.getVariable(taskBeforeSubProcess.getProcessInstanceId(),"subVariable"));
  assertEquals("Hello from the super process.",taskService.getVariable(taskBeforeSubProcess.getId(),"subVariable"));
  runtimeService.setVariable(taskBeforeSubProcess.getProcessInstanceId(),"subVariable","Hello from sub process.");
  assertEquals("Hello from the super process.",runtimeService.getVariable(processInstance.getId(),"superVariable"));
  taskService.complete(taskBeforeSubProcess.getId());
  Task taskAfterSubProcess=taskQuery.singleResult();
  assertEquals("Task in super process",taskAfterSubProcess.getName());
  assertEquals("Hello from sub process.",runtimeService.getVariable(processInstance.getId(),"superVariable"));
  assertEquals("Hello from sub process.",taskService.getVariable(taskAfterSubProcess.getId(),"superVariable"));
  vars.clear();
  vars.put("x",5l);
  taskService.complete(taskAfterSubProcess.getId(),vars);
  Task taskInSecondSubProcess=taskQuery.singleResult();
  assertEquals("Task in subprocess",taskInSecondSubProcess.getName());
  assertEquals(10l,runtimeService.getVariable(taskInSecondSubProcess.getProcessInstanceId(),"y"));
  assertEquals(10l,taskService.getVariable(taskInSecondSubProcess.getId(),"y"));
  taskService.complete(taskInSecondSubProcess.getId());
  Task taskAfterSecondSubProcess=taskQuery.singleResult();
  assertEquals("Task in super process",taskAfterSecondSubProcess.getName());
  assertEquals(15l,runtimeService.getVariable(taskAfterSecondSubProcess.getProcessInstanceId(),"z"));
  assertEquals(15l,taskService.getVariable(taskAfterSecondSubProcess.getId(),"z"));
  taskService.complete(taskAfterSecondSubProcess.getId());
  assertProcessEnded(processInstance.getId());
  assertEquals(0,runtimeService.createExecutionQuery().list().size());
}
