@Deployment(resources={TEST_PROCESS}) public void testSetProcessDefinitionVersionNonExistingPD(){
  ProcessInstance pi=runtimeService.startProcessInstanceByKey("receiveTask");
  CommandExecutor commandExecutor=processEngineConfiguration.getCommandExecutor();
  try {
    commandExecutor.execute(new SetProcessDefinitionVersionCmd(pi.getId(),23));
    fail("ActivitiException expected");
  }
 catch (  ActivitiObjectNotFoundException ae) {
    assertTextPresent("no processes deployed with key = 'receiveTask' and version = '23'",ae.getMessage());
    assertEquals(ProcessDefinition.class,ae.getObjectClass());
  }
}
