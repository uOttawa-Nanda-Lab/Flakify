@Test public void testRetryInterceptor(){
  try {
    processEngine.getManagementService().executeCommand(new CommandThrowingOptimisticLockingException());
    Assert.fail("ActivitiException expected.");
  }
 catch (  ActivitiException e) {
    Assert.assertTrue(e.getMessage().contains(retryInterceptor.getNumOfRetries() + " retries failed"));
  }
  Assert.assertEquals(retryInterceptor.getNumOfRetries() + 1,counter.get());
}
