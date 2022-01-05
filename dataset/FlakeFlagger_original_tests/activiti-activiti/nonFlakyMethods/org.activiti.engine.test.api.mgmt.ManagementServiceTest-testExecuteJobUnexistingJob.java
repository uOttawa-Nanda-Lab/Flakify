public void testExecuteJobUnexistingJob(){
  try {
    managementService.executeJob("unexistingjob");
    fail("ActivitiException expected");
  }
 catch (  JobNotFoundException jnfe) {
    assertTextPresent("No job found with id",jnfe.getMessage());
    assertEquals(Job.class,jnfe.getObjectClass());
  }
}
