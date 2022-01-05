@Deployment(resources={"org/activiti/engine/test/bpmn/multiinstance/MultiInstanceTest.testSequentialSubprocessEmptyCollection.bpmn20.xml"}) public void testSequentialSubprocessEmptyCollection(){
  Collection<String> collection=Collections.emptyList();
  Map<String,Object> variableMap=new HashMap<String,Object>();
  variableMap.put("collection",collection);
  ProcessInstance processInstance=runtimeService.startProcessInstanceByKey("testSequentialSubProcessEmptyCollection",variableMap);
  assertNotNull(processInstance);
  Task task=taskService.createTaskQuery().singleResult();
  assertNull(task);
  assertProcessEnded(processInstance.getId());
}
