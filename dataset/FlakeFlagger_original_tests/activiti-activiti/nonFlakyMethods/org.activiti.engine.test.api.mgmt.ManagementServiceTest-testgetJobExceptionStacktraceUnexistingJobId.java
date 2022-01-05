public void testgetJobExceptionStacktraceUnexistingJobId(){
  try {
    managementService.getJobExceptionStacktrace("unexistingjob");
    fail("ActivitiException expected");
  }
 catch (  ActivitiObjectNotFoundException re) {
    assertTextPresent("No job found with id unexistingjob",re.getMessage());
    assertEquals(Job.class,re.getObjectClass());
  }
}
