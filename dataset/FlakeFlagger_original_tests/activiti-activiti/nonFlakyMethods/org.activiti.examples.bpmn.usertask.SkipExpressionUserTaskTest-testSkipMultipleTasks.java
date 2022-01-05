@Deployment public void testSkipMultipleTasks(){
  Map<String,Object> variables=new HashMap<String,Object>();
  variables.put("_ACTIVITI_SKIP_EXPRESSION_ENABLED",true);
  variables.put("skip1",true);
  variables.put("skip2",true);
  variables.put("skip3",false);
  runtimeService.startProcessInstanceByKey("skipExpressionUserTask-testSkipMultipleTasks",variables);
  List<Task> tasks=taskService.createTaskQuery().list();
  assertEquals(1,tasks.size());
  assertEquals("Task3",tasks.get(0).getName());
}
