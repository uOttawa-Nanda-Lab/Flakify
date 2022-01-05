public void testExecuteJobNullJobId(){
  try {
    managementService.executeJob(null);
    fail("ActivitiException expected");
  }
 catch (  ActivitiIllegalArgumentException re) {
    assertTextPresent("JobId is null",re.getMessage());
  }
}
