public void testSetJobRetriesUnexistingJobId(){
  try {
    managementService.setJobRetries("unexistingjob",5);
    fail("ActivitiException expected");
  }
 catch (  ActivitiObjectNotFoundException re) {
    assertTextPresent("No job found with id 'unexistingjob'.",re.getMessage());
    assertEquals(Job.class,re.getObjectClass());
  }
}
