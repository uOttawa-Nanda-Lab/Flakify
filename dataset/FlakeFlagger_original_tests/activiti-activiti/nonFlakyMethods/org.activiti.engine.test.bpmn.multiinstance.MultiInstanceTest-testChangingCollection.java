@Deployment public void testChangingCollection(){
  Map<String,Object> vars=new HashMap<String,Object>();
  vars.put("multi_users",Arrays.asList("testuser"));
  ProcessInstance instance=runtimeService.startProcessInstanceByKey("test_multi",vars);
  assertNotNull(instance);
  Task task=taskService.createTaskQuery().singleResult();
  assertEquals("multi",task.getTaskDefinitionKey());
  vars.put("multi_users",new ArrayList<String>());
  taskService.complete(task.getId(),vars);
  List<ProcessInstance> instances=runtimeService.createProcessInstanceQuery().list();
  assertEquals(0,instances.size());
}
