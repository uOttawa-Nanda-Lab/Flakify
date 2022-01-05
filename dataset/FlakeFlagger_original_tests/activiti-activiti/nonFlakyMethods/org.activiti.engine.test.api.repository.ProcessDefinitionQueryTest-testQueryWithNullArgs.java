public void testQueryWithNullArgs(){
  try {
    repositoryService.createProcessDefinitionQuery().processDefinitionKeys(null);
    fail("Expected exception not thrown");
  }
 catch (  ActivitiIllegalArgumentException ex) {
    return;
  }
}
