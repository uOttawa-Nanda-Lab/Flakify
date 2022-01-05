@Deployment(resources={"org/activiti/engine/test/api/oneTaskProcess.bpmn20.xml"}) public void testLargeAmountOfVariableBulkDelete() throws Exception {
  Map<String,Object> variables=new HashMap<String,Object>();
  for (int i=0; i < 4001; i++) {
    variables.put("var" + i,i);
  }
  ProcessInstance processInstance=runtimeService.startProcessInstanceByKey("oneTaskProcess",variables);
  Task task=taskService.createTaskQuery().processInstanceId(processInstance.getId()).singleResult();
  assertNotNull(task);
  taskService.complete(task.getId());
  assertEquals(0L,runtimeService.createProcessInstanceQuery().processInstanceId(processInstance.getId()).count());
}
