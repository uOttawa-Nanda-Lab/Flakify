@Test public void executeShouldDeleteIntegrationContext() throws Exception {
  IntegrationContextEntity integrationContextEntity=mock(IntegrationContextEntity.class);
  DeleteIntegrationContextCmd command=new DeleteIntegrationContextCmd(integrationContextEntity);
  command.execute(commandContext);
  verify(integrationContextManager).delete(integrationContextEntity);
}
