public void testQueryByKeys(){
  Set<String> one=new HashSet<String>();
  one.add("one");
  Set<String> two=new HashSet<String>();
  two.add("two");
  Set<String> oneAndTwo=new HashSet<String>();
  oneAndTwo.addAll(one);
  oneAndTwo.addAll(two);
  ProcessDefinitionQuery query=repositoryService.createProcessDefinitionQuery().processDefinitionKeys(one);
  verifyQueryResults(query,2);
  query=repositoryService.createProcessDefinitionQuery().processDefinitionKeys(two);
  verifyQueryResults(query,1);
  query=repositoryService.createProcessDefinitionQuery().processDefinitionKeys(oneAndTwo);
  verifyQueryResults(query,3);
}
