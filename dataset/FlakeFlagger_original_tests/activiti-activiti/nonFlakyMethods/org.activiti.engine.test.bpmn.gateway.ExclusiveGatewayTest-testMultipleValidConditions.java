@Deployment public void testMultipleValidConditions(){
  runtimeService.startProcessInstanceByKey("exclusiveGwMultipleValidConditions",CollectionUtil.singletonMap("input",5));
  assertEquals("Task 2",taskService.createTaskQuery().singleResult().getName());
}
