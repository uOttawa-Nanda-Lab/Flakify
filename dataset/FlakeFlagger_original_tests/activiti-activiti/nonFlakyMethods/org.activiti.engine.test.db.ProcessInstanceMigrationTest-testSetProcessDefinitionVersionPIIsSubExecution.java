@Deployment(resources={TEST_PROCESS_WITH_PARALLEL_GATEWAY}) public void testSetProcessDefinitionVersionPIIsSubExecution(){
  ProcessInstance pi=runtimeService.startProcessInstanceByKey("forkJoin");
  Execution execution=runtimeService.createExecutionQuery().activityId("receivePayment").singleResult();
  CommandExecutor commandExecutor=processEngineConfiguration.getCommandExecutor();
  SetProcessDefinitionVersionCmd command=new SetProcessDefinitionVersionCmd(execution.getId(),1);
  try {
    commandExecutor.execute(command);
    fail("ActivitiException expected");
  }
 catch (  ActivitiException ae) {
    assertTextPresent("A process instance id is required, but the provided id '" + execution.getId() + "' points to a child execution of process instance '"+ pi.getId()+ "'. Please invoke the "+ command.getClass().getSimpleName()+ " with a root execution id.",ae.getMessage());
  }
}
