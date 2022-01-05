public void testSetProcessDefinitionVersionNonExistingPI(){
  CommandExecutor commandExecutor=processEngineConfiguration.getCommandExecutor();
  try {
    commandExecutor.execute(new SetProcessDefinitionVersionCmd("42",23));
    fail("ActivitiException expected");
  }
 catch (  ActivitiObjectNotFoundException ae) {
    assertTextPresent("No process instance found for id = '42'.",ae.getMessage());
    assertEquals(ProcessInstance.class,ae.getObjectClass());
  }
}
