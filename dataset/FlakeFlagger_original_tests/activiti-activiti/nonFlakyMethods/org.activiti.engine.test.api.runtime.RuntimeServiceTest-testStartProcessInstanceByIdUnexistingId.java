public void testStartProcessInstanceByIdUnexistingId(){
  try {
    runtimeService.startProcessInstanceById("unexistingId");
    fail("ActivitiException expected");
  }
 catch (  ActivitiObjectNotFoundException ae) {
    assertTextPresent("no deployed process definition found with id",ae.getMessage());
    assertEquals(ProcessDefinition.class,ae.getObjectClass());
  }
}
