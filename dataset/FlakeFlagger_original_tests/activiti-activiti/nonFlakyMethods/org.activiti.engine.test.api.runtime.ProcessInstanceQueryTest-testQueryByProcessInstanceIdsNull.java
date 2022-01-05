public void testQueryByProcessInstanceIdsNull(){
  try {
    runtimeService.createProcessInstanceQuery().processInstanceIds(null);
    fail("ActivitiException expected");
  }
 catch (  ActivitiIllegalArgumentException re) {
    assertTextPresent("Set of process instance ids is null",re.getMessage());
  }
}
