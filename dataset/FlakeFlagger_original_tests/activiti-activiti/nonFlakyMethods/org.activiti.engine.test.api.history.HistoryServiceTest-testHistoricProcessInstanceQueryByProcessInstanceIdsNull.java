public void testHistoricProcessInstanceQueryByProcessInstanceIdsNull(){
  try {
    historyService.createHistoricProcessInstanceQuery().processInstanceIds(null);
    fail("ActivitiException expected");
  }
 catch (  ActivitiIllegalArgumentException re) {
    assertTextPresent("Set of process instance ids is null",re.getMessage());
  }
}
