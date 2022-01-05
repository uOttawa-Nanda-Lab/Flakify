public void testQueryByProcessInstanceIdsEmpty(){
  try {
    runtimeService.createProcessInstanceQuery().processInstanceIds(new HashSet<String>());
    fail("ActivitiException expected");
  }
 catch (  ActivitiIllegalArgumentException re) {
    assertTextPresent("Set of process instance ids is empty",re.getMessage());
  }
}
