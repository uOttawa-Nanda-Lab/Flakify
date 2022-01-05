public void testSetJobRetriesNegativeNumberOfRetries(){
  try {
    managementService.setJobRetries("unexistingjob",-1);
    fail("ActivitiException expected");
  }
 catch (  ActivitiIllegalArgumentException re) {
    assertTextPresent("The number of job retries must be a non-negative Integer, but '-1' has been provided.",re.getMessage());
  }
}
