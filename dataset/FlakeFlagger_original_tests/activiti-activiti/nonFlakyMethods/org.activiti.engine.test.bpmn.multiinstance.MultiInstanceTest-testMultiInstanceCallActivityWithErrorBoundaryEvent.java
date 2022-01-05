@Deployment(resources={"org/activiti/engine/test/bpmn/multiinstance/MultiInstanceTest.callActivityWithBoundaryErrorEvent.bpmn20.xml","org/activiti/engine/test/bpmn/multiinstance/MultiInstanceTest.throwingErrorEventSubProcess.bpmn20.xml"}) public void testMultiInstanceCallActivityWithErrorBoundaryEvent(){
  Map<String,Object> variableMap=new HashMap<String,Object>();
  variableMap.put("assignees",Arrays.asList("kermit","gonzo"));
  ProcessInstance processInstance=runtimeService.startProcessInstanceByKey("process",variableMap);
  List<Task> tasks=taskService.createTaskQuery().list();
  assertEquals(2,tasks.size());
  variableMap=new HashMap<String,Object>();
  variableMap.put("done",false);
  taskService.complete(tasks.get(0).getId(),variableMap);
  tasks=taskService.createTaskQuery().list();
  assertEquals(1,tasks.size());
  taskService.complete(tasks.get(0).getId());
  List<ProcessInstance> processInstances=runtimeService.createProcessInstanceQuery().processDefinitionKey("process").list();
  assertEquals(0,processInstances.size());
  assertProcessEnded(processInstance.getId());
}
