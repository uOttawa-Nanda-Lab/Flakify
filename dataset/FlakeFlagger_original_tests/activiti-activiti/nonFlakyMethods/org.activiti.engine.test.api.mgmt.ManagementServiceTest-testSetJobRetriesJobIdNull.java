public void testSetJobRetriesJobIdNull(){
  try {
    managementService.setJobRetries(null,5);
    fail("ActivitiException expected");
  }
 catch (  ActivitiIllegalArgumentException re) {
    assertTextPresent("The job id is mandatory, but 'null' has been provided.",re.getMessage());
  }
}
