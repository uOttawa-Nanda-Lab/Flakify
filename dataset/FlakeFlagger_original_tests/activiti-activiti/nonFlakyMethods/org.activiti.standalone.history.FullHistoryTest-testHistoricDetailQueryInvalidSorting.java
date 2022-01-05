public void testHistoricDetailQueryInvalidSorting() throws Exception {
  try {
    historyService.createHistoricDetailQuery().asc().list();
    fail();
  }
 catch (  ActivitiIllegalArgumentException e) {
  }
  try {
    historyService.createHistoricDetailQuery().desc().list();
    fail();
  }
 catch (  ActivitiIllegalArgumentException e) {
  }
  try {
    historyService.createHistoricDetailQuery().orderByProcessInstanceId().list();
    fail();
  }
 catch (  ActivitiIllegalArgumentException e) {
  }
  try {
    historyService.createHistoricDetailQuery().orderByTime().list();
    fail();
  }
 catch (  ActivitiIllegalArgumentException e) {
  }
  try {
    historyService.createHistoricDetailQuery().orderByVariableName().list();
    fail();
  }
 catch (  ActivitiIllegalArgumentException e) {
  }
  try {
    historyService.createHistoricDetailQuery().orderByVariableRevision().list();
    fail();
  }
 catch (  ActivitiIllegalArgumentException e) {
  }
  try {
    historyService.createHistoricDetailQuery().orderByVariableType().list();
    fail();
  }
 catch (  ActivitiIllegalArgumentException e) {
  }
}
