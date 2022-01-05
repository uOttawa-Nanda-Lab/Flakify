@Deployment public void testFlowsInAdhocSubProcess(){
  Map<String,Object> variableMap=new HashMap<String,Object>();
  variableMap.put("completed",false);
  ProcessInstance pi=runtimeService.startProcessInstanceByKey("simpleSubProcess",variableMap);
  Execution execution=runtimeService.createExecutionQuery().activityId("adhocSubProcess").singleResult();
  assertNotNull(execution);
  List<FlowNode> enabledActivities=runtimeService.getEnabledActivitiesFromAdhocSubProcess(execution.getId());
  assertEquals(2,enabledActivities.size());
  runtimeService.executeActivityInAdhocSubProcess(execution.getId(),"subProcessTask");
  Task subProcessTask=taskService.createTaskQuery().processInstanceId(pi.getId()).singleResult();
  assertEquals("Task in subprocess",subProcessTask.getName());
  taskService.complete(subProcessTask.getId());
  try {
    runtimeService.executeActivityInAdhocSubProcess(execution.getId(),"subProcessTask2");
    fail("exception expected because can only enable one activity in a sequential ad-hoc sub process");
  }
 catch (  ActivitiException e) {
  }
  subProcessTask=taskService.createTaskQuery().processInstanceId(pi.getId()).singleResult();
  assertEquals("The next task",subProcessTask.getName());
  variableMap=new HashMap<String,Object>();
  variableMap.put("completed",true);
  taskService.complete(subProcessTask.getId(),variableMap);
  Task afterTask=taskService.createTaskQuery().processInstanceId(pi.getId()).singleResult();
  assertEquals("After task",afterTask.getName());
  taskService.complete(afterTask.getId());
  assertNull(runtimeService.createProcessInstanceQuery().processInstanceId(pi.getId()).singleResult());
}
