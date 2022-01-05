@Deployment public void testCandidateExpressionOneParam(){
  Map<String,Object> params=new HashMap<String,Object>();
  params.put("testBean",new TestBean());
  runtimeService.startProcessInstanceByKey("candidateWithExpression",params);
  assertEquals(1,taskService.createTaskQuery().taskCandidateUser(KERMIT,KERMITSGROUPS).list().size());
}
