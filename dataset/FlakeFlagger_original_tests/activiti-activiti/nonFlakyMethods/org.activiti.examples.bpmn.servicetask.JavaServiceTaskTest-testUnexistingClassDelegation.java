@Deployment public void testUnexistingClassDelegation(){
  try {
    runtimeService.startProcessInstanceByKey("unexistingClassDelegation");
    fail();
  }
 catch (  ActivitiException e) {
    assertTrue(e.getMessage().contains("couldn't instantiate class org.activiti.BogusClass"));
    assertNotNull(e.getCause());
    assertTrue(e.getCause() instanceof ActivitiClassLoadingException);
  }
}
