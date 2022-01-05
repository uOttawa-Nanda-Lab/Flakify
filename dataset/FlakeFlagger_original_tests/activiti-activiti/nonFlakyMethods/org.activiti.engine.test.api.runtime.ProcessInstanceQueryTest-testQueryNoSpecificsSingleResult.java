public void testQueryNoSpecificsSingleResult(){
  ProcessInstanceQuery query=runtimeService.createProcessInstanceQuery();
  try {
    query.singleResult();
    fail();
  }
 catch (  ActivitiException e) {
  }
}
