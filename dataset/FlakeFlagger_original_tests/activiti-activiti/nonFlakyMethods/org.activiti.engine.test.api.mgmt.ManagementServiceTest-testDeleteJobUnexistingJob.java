public void testDeleteJobUnexistingJob(){
  try {
    managementService.deleteJob("unexistingjob");
    fail("ActivitiException expected");
  }
 catch (  ActivitiObjectNotFoundException ae) {
    assertTextPresent("No job found with id",ae.getMessage());
    assertEquals(Job.class,ae.getObjectClass());
  }
}
