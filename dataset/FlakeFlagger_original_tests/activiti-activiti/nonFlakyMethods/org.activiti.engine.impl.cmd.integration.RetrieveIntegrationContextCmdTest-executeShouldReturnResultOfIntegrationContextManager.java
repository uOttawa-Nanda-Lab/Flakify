@Test public void executeShouldReturnResultOfIntegrationContextManager(){
  String id="id";
  RetrieveIntegrationContextsCmd command=new RetrieveIntegrationContextsCmd(id);
  IntegrationContextEntity contextEntity=mock(IntegrationContextEntity.class);
  given(integrationContextManager.findById(id)).willReturn(contextEntity);
  IntegrationContextEntity executeResult=command.execute(commandContext);
  assertThat(executeResult).isEqualTo(contextEntity);
}
