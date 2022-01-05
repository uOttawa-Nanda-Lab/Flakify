public void testDeleteJobNullJobId(){
  try {
    managementService.deleteJob(null);
    fail("ActivitiException expected");
  }
 catch (  ActivitiIllegalArgumentException re) {
    assertTextPresent("jobId is null",re.getMessage());
  }
}
