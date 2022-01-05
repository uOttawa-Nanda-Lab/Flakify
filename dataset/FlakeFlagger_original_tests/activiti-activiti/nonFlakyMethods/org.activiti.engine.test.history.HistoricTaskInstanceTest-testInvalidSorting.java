public void testInvalidSorting(){
  try {
    historyService.createHistoricTaskInstanceQuery().asc();
    fail();
  }
 catch (  ActivitiIllegalArgumentException e) {
  }
  try {
    historyService.createHistoricTaskInstanceQuery().desc();
    fail();
  }
 catch (  ActivitiIllegalArgumentException e) {
  }
  try {
    historyService.createHistoricTaskInstanceQuery().orderByProcessInstanceId().list();
    fail();
  }
 catch (  ActivitiIllegalArgumentException e) {
  }
}
