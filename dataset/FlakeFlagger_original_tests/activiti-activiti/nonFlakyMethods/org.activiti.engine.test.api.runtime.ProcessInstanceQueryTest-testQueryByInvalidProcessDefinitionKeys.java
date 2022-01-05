public void testQueryByInvalidProcessDefinitionKeys(){
  try {
    runtimeService.createProcessInstanceQuery().processDefinitionKeys(null);
    fail();
  }
 catch (  ActivitiException e) {
  }
  try {
    runtimeService.createProcessInstanceQuery().processDefinitionKeys(Collections.<String>emptySet());
    fail();
  }
 catch (  ActivitiException e) {
  }
}
