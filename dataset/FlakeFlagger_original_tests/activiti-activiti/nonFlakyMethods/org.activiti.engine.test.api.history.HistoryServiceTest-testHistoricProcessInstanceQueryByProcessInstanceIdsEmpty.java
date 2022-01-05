public void testHistoricProcessInstanceQueryByProcessInstanceIdsEmpty(){
  try {
    historyService.createHistoricProcessInstanceQuery().processInstanceIds(new HashSet<String>());
    fail("ActivitiException expected");
  }
 catch (  ActivitiIllegalArgumentException re) {
    assertTextPresent("Set of process instance ids is empty",re.getMessage());
  }
}
