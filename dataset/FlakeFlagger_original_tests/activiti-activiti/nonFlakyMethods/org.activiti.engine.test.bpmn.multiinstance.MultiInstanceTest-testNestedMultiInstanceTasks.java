@Deployment(resources={"org/activiti/engine/test/bpmn/multiinstance/MultiInstanceTest.testNestedMultiInstanceTasks.bpmn20.xml"}) public void testNestedMultiInstanceTasks(){
  List<String> processes=Arrays.asList("process A","process B");
  List<String> assignees=Arrays.asList("kermit","gonzo");
  Map<String,Object> variableMap=new HashMap<String,Object>();
  variableMap.put("subProcesses",processes);
  variableMap.put("assignees",assignees);
  ProcessInstance processInstance=runtimeService.startProcessInstanceByKey("miNestedMultiInstanceTasks",variableMap);
  List<Task> tasks=taskService.createTaskQuery().list();
  assertEquals(processes.size() * assignees.size(),tasks.size());
  for (  Task t : tasks) {
    taskService.complete(t.getId());
  }
  List<ProcessInstance> processInstances=runtimeService.createProcessInstanceQuery().processDefinitionKey("miNestedMultiInstanceTasks").list();
  assertEquals(0,processInstances.size());
  assertProcessEnded(processInstance.getId());
}
