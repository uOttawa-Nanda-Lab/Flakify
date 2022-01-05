public void testgetJobExceptionStacktraceNullJobId(){
  try {
    managementService.getJobExceptionStacktrace(null);
    fail("ActivitiException expected");
  }
 catch (  ActivitiIllegalArgumentException re) {
    assertTextPresent("jobId is null",re.getMessage());
  }
}
