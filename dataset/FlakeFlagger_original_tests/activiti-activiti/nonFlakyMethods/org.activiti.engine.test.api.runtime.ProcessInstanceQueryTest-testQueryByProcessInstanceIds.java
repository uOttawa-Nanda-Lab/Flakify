public void testQueryByProcessInstanceIds(){
  Set<String> processInstanceIds=new HashSet<String>(this.processInstanceIds);
  runtimeService.startProcessInstanceByKey("oneTaskProcess2","2");
  ProcessInstanceQuery processInstanceQuery=runtimeService.createProcessInstanceQuery().processInstanceIds(processInstanceIds);
  assertEquals(5,processInstanceQuery.count());
  List<ProcessInstance> processInstances=processInstanceQuery.list();
  assertNotNull(processInstances);
  assertEquals(5,processInstances.size());
  for (  ProcessInstance processInstance : processInstances) {
    assertTrue(processInstanceIds.contains(processInstance.getId()));
  }
}
