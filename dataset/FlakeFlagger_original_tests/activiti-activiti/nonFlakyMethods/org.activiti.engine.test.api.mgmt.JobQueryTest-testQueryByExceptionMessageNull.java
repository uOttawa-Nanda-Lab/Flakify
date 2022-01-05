public void testQueryByExceptionMessageNull(){
  try {
    managementService.createJobQuery().exceptionMessage(null);
    fail("ActivitiException expected");
  }
 catch (  ActivitiIllegalArgumentException e) {
    assertEquals("Provided exception message is null",e.getMessage());
  }
}
