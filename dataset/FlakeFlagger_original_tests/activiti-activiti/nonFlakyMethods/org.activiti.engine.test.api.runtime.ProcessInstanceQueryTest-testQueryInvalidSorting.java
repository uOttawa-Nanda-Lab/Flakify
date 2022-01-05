public void testQueryInvalidSorting(){
  try {
    runtimeService.createProcessInstanceQuery().orderByProcessDefinitionId().list();
    fail();
  }
 catch (  ActivitiIllegalArgumentException e) {
  }
}
