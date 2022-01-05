public void testStartProcessInstanceByKeyUnexistingKey(){
  try {
    runtimeService.startProcessInstanceByKey("unexistingkey");
    fail("ActivitiException expected");
  }
 catch (  ActivitiObjectNotFoundException ae) {
    assertTextPresent("no processes deployed with key",ae.getMessage());
    assertEquals(ProcessDefinition.class,ae.getObjectClass());
  }
}
