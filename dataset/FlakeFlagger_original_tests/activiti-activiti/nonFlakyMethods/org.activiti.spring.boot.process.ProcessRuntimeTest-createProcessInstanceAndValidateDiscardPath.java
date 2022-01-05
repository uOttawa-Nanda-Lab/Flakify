@Test @WithUserDetails(value="salaboy",userDetailsServiceBeanName="myUserDetailsService") public void createProcessInstanceAndValidateDiscardPath(){
  ProcessInstance categorizeProcess=processRuntime.start(ProcessPayloadBuilder.start().withProcessDefinitionKey(CATEGORIZE_PROCESS).withVariable("expectedKey",false).build());
  assertThat(RuntimeTestConfiguration.completedProcesses).contains(categorizeProcess.getId());
  assertThat(categorizeProcess).isNotNull();
  assertThat(categorizeProcess.getStatus()).isEqualTo(ProcessInstance.ProcessInstanceStatus.COMPLETED);
  assertThat(RuntimeTestConfiguration.processImageConnectorExecuted).isEqualTo(true);
  assertThat(RuntimeTestConfiguration.tagImageConnectorExecuted).isEqualTo(false);
  assertThat(RuntimeTestConfiguration.discardImageConnectorExecuted).isEqualTo(true);
}
