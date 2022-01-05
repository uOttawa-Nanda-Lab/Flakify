public void testQueryInvalidSorting(){
  try {
    runtimeService.createExecutionQuery().orderByProcessDefinitionKey().list();
    fail();
  }
 catch (  ActivitiException e) {
  }
}
