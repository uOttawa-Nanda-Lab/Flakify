@Deployment public void testSetAssigneeThroughSpringService(){
  runtimeService.startProcessInstanceByKey("assigneeThroughSpringService",CollectionUtil.singletonMap("emp","fozzie"));
  assertEquals(1,taskService.createTaskQuery().taskAssignee("Kermit The Frog").count());
}
