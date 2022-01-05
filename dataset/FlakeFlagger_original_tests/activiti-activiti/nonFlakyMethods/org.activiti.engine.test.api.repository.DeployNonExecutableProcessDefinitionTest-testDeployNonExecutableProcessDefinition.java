@Deployment public void testDeployNonExecutableProcessDefinition(){
  try {
    runtimeService.startProcessInstanceByKey("oneTaskProcessNonExecutable");
    fail();
  }
 catch (  ActivitiException e) {
    assertTextPresent("no processes deployed with key 'oneTaskProcessNonExecutable'",e.getMessage());
  }
}
