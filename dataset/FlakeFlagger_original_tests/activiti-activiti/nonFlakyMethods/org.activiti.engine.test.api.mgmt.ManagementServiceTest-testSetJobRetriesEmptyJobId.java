public void testSetJobRetriesEmptyJobId(){
  try {
    managementService.setJobRetries("",5);
    fail("ActivitiException expected");
  }
 catch (  ActivitiIllegalArgumentException re) {
    assertTextPresent("The job id is mandatory, but '' has been provided.",re.getMessage());
  }
}
