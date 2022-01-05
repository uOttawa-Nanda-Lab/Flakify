@Deployment public void testCandidateExpressionTwoParams(){
  Map<String,Object> params=new HashMap<String,Object>();
  params.put("testBean",new TestBean());
  runtimeService.startProcessInstanceByKey("candidateWithExpression",params);
  assertEquals(1,taskService.createTaskQuery().taskCandidateUser(KERMIT,KERMITSGROUPS).count());
  assertEquals(1,taskService.createTaskQuery().taskCandidateGroup("sales").count());
}
