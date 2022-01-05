@Deployment(resources={"org/activiti/engine/test/bpmn/multiinstance/MultiInstanceTest.testParallelEmptyCollection.bpmn20.xml"}) public void testParalellEmptyCollection() throws Exception {
  Collection<String> collection=Collections.emptyList();
  Map<String,Object> variableMap=new HashMap<String,Object>();
  variableMap.put("collection",collection);
  ProcessInstance processInstance=runtimeService.startProcessInstanceByKey("testParalellEmptyCollection",variableMap);
  assertNotNull(processInstance);
  Task task=taskService.createTaskQuery().singleResult();
  assertNull(task);
  assertProcessEnded(processInstance.getId());
}
