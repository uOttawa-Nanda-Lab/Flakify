@Deployment public void testSimpleAdhocSubProcess(){
  ProcessInstance pi=runtimeService.startProcessInstanceByKey("simpleSubProcess");
  Execution execution=runtimeService.createExecutionQuery().activityId("adhocSubProcess").singleResult();
  assertNotNull(execution);
  List<FlowNode> enabledActivities=runtimeService.getEnabledActivitiesFromAdhocSubProcess(execution.getId());
  assertEquals(2,enabledActivities.size());
  Execution newTaskExecution=runtimeService.executeActivityInAdhocSubProcess(execution.getId(),"subProcessTask");
  assertNotNull(newTaskExecution);
  assertNotNull(newTaskExecution.getId());
  Task subProcessTask=taskService.createTaskQuery().processInstanceId(pi.getId()).taskDefinitionKey("subProcessTask").singleResult();
  assertEquals("Task in subprocess",subProcessTask.getName());
  taskService.complete(subProcessTask.getId());
  enabledActivities=runtimeService.getEnabledActivitiesFromAdhocSubProcess(execution.getId());
  assertEquals(2,enabledActivities.size());
  runtimeService.completeAdhocSubProcess(execution.getId());
  Task afterTask=taskService.createTaskQuery().processInstanceId(pi.getId()).singleResult();
  assertEquals("After task",afterTask.getName());
  taskService.complete(afterTask.getId());
  assertNull(runtimeService.createProcessInstanceQuery().processInstanceId(pi.getId()).singleResult());
}
