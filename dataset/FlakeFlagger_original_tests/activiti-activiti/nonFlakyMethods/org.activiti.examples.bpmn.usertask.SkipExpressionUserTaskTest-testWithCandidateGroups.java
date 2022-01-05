@Deployment public void testWithCandidateGroups(){
  Map<String,Object> vars=new HashMap<String,Object>();
  vars.put("_ACTIVITI_SKIP_EXPRESSION_ENABLED",true);
  vars.put("skip",true);
  runtimeService.startProcessInstanceByKey("skipExpressionUserTask",vars);
  assertEquals(0,taskService.createTaskQuery().list().size());
}
