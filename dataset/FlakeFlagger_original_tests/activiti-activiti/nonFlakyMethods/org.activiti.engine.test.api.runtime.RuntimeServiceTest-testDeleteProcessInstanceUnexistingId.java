public void testDeleteProcessInstanceUnexistingId(){
  try {
    runtimeService.deleteProcessInstance("enexistingInstanceId",null);
    fail("ActivitiException expected");
  }
 catch (  ActivitiObjectNotFoundException ae) {
    assertTextPresent("No process instance found for id",ae.getMessage());
    assertEquals(ProcessInstance.class,ae.getObjectClass());
  }
}
