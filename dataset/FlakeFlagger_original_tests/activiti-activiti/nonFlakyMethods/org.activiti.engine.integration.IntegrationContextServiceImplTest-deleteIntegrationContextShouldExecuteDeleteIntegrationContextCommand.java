@Test public void deleteIntegrationContextShouldExecuteDeleteIntegrationContextCommand(){
  IntegrationContextEntity entity=mock(IntegrationContextEntity.class);
  integrationContextService.deleteIntegrationContext(entity);
  verify(commandExecutor).execute(any(DeleteIntegrationContextCmd.class));
}
