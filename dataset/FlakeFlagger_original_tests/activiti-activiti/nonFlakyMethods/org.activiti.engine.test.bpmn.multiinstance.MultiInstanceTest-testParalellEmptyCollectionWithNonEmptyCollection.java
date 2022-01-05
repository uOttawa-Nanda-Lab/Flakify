@Deployment(resources={"org/activiti/engine/test/bpmn/multiinstance/MultiInstanceTest.testParallelEmptyCollection.bpmn20.xml"}) public void testParalellEmptyCollectionWithNonEmptyCollection(){
  Collection<String> collection=Collections.singleton("Test");
  Map<String,Object> variableMap=new HashMap<String,Object>();
  variableMap.put("collection",collection);
  ProcessInstance processInstance=runtimeService.startProcessInstanceByKey("testParalellEmptyCollection",variableMap);
  assertNotNull(processInstance);
  Task task=taskService.createTaskQuery().singleResult();
  assertNotNull(task);
  taskService.complete(task.getId());
  assertProcessEnded(processInstance.getId());
}
