public void testQueryByInvalidProcessDefinitionIds(){
  try {
    runtimeService.createProcessInstanceQuery().processDefinitionIds(null);
    fail();
  }
 catch (  ActivitiException e) {
  }
  try {
    runtimeService.createProcessInstanceQuery().processDefinitionIds(Collections.<String>emptySet());
    fail();
  }
 catch (  ActivitiException e) {
  }
}
