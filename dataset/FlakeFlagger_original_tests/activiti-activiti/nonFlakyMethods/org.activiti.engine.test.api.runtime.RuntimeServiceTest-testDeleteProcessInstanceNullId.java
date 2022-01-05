public void testDeleteProcessInstanceNullId(){
  try {
    runtimeService.deleteProcessInstance(null,"test null id delete");
    fail("ActivitiException expected");
  }
 catch (  ActivitiIllegalArgumentException ae) {
    assertTextPresent("processInstanceId is null",ae.getMessage());
  }
}
