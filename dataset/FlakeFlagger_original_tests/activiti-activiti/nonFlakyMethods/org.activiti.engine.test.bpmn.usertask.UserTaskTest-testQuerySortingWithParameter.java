@Deployment public void testQuerySortingWithParameter(){
  ProcessInstance processInstance=runtimeService.startProcessInstanceByKey("oneTaskProcess");
  assertEquals(1,taskService.createTaskQuery().processInstanceId(processInstance.getId()).list().size());
}
