@Test public void findByIdShouldExecuteRetrieveIntegrationContextCmd(){
  IntegrationContextEntity entity=mock(IntegrationContextEntity.class);
  given(commandExecutor.execute(any(RetrieveIntegrationContextsCmd.class))).willReturn(entity);
  IntegrationContextEntity commandResult=integrationContextService.findById("id");
  assertThat(commandResult).isEqualTo(entity);
}
