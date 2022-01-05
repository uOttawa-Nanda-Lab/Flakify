@Deployment public void testExclusiveDirectlyToEnd(){
  Map<String,Object> variables=new HashMap<String,Object>();
  variables.put("input",1);
  ProcessInstance startProcessInstanceByKey=runtimeService.startProcessInstanceByKey("exclusiveGateway",variables);
  long count=historyService.createHistoricActivityInstanceQuery().processInstanceId(startProcessInstanceByKey.getId()).unfinished().count();
  assertEquals(0,count);
}
