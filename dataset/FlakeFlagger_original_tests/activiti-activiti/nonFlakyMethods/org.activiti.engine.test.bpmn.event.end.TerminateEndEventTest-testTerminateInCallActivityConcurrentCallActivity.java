@Deployment(resources={"org/activiti/engine/test/bpmn/event/end/TerminateEndEventTest.testTerminateInCallActivityConcurrentCallActivity.bpmn","org/activiti/engine/test/bpmn/event/end/TerminateEndEventTest.testTerminateAfterUserTask.bpmn","org/activiti/engine/test/api/oneTaskProcess.bpmn20.xml"}) public void testTerminateInCallActivityConcurrentCallActivity() throws Exception {
  ProcessInstance pi=runtimeService.startProcessInstanceByKey("terminateEndEventInCallActivityConcurrentCallActivity");
  assertThat(runtimeService.createProcessInstanceQuery().superProcessInstanceId(pi.getId()).list().size(),is(2));
  Task preTerminate=taskService.createTaskQuery().taskName("preTerminate").singleResult();
  taskService.complete(preTerminate.getId());
  assertEquals(1,runtimeService.createProcessInstanceQuery().processInstanceId(pi.getId()).count());
}
