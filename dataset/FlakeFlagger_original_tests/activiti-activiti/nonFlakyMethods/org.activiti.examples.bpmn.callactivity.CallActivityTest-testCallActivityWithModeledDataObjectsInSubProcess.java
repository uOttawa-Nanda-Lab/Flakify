@Deployment(resources={"org/activiti/examples/bpmn/callactivity/mainProcess.bpmn20.xml","org/activiti/examples/bpmn/callactivity/childProcess.bpmn20.xml"}) public void testCallActivityWithModeledDataObjectsInSubProcess(){
  ProcessInstance pi=runtimeService.startProcessInstanceByKey("mainProcess");
  TaskQuery taskQuery=taskService.createTaskQuery();
  Task verifyCreditTask=taskQuery.singleResult();
  assertEquals("User Task 1",verifyCreditTask.getName());
  ProcessInstance subProcessInstance=runtimeService.createProcessInstanceQuery().superProcessInstanceId(pi.getId()).singleResult();
  assertNotNull(subProcessInstance);
  assertEquals(pi.getId(),runtimeService.createProcessInstanceQuery().subProcessInstanceId(subProcessInstance.getId()).singleResult().getId());
  assertEquals("Batman",runtimeService.getVariable(subProcessInstance.getId(),"Name"));
}
