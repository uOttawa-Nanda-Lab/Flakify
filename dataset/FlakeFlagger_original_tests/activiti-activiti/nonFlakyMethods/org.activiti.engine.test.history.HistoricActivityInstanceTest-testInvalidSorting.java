public void testInvalidSorting(){
  try {
    historyService.createHistoricActivityInstanceQuery().asc().list();
    fail();
  }
 catch (  ActivitiIllegalArgumentException e) {
  }
  try {
    historyService.createHistoricActivityInstanceQuery().desc().list();
    fail();
  }
 catch (  ActivitiIllegalArgumentException e) {
  }
  try {
    historyService.createHistoricActivityInstanceQuery().orderByHistoricActivityInstanceDuration().list();
    fail();
  }
 catch (  ActivitiIllegalArgumentException e) {
  }
}
