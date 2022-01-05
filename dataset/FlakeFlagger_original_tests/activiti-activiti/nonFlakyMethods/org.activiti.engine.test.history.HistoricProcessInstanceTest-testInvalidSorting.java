public void testInvalidSorting(){
  try {
    historyService.createHistoricProcessInstanceQuery().asc();
    fail();
  }
 catch (  ActivitiIllegalArgumentException e) {
  }
  try {
    historyService.createHistoricProcessInstanceQuery().desc();
    fail();
  }
 catch (  ActivitiIllegalArgumentException e) {
  }
  try {
    historyService.createHistoricProcessInstanceQuery().orderByProcessInstanceId().list();
    fail();
  }
 catch (  ActivitiIllegalArgumentException e) {
  }
}
