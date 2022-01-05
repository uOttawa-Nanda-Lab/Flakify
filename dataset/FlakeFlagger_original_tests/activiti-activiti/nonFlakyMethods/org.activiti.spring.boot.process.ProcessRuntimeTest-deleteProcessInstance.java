@Test @WithUserDetails(value="salaboy",userDetailsServiceBeanName="myUserDetailsService") public void deleteProcessInstance(){
  ProcessRuntimeConfiguration configuration=processRuntime.configuration();
  assertThat(configuration).isNotNull();
  Page<ProcessDefinition> processDefinitionPage=processRuntime.processDefinitions(Pageable.of(0,50));
  assertThat(processDefinitionPage.getContent()).isNotNull();
  assertThat(processDefinitionPage.getContent()).extracting((  ProcessDefinition pd) -> pd.getKey()).contains(CATEGORIZE_HUMAN_PROCESS);
  ProcessInstance categorizeProcess=processRuntime.start(ProcessPayloadBuilder.start().withProcessDefinitionKey(CATEGORIZE_HUMAN_PROCESS).withVariable("expectedKey",true).withBusinessKey("my business key").build());
  assertThat(categorizeProcess).isNotNull();
  assertThat(categorizeProcess.getStatus()).isEqualTo(ProcessInstance.ProcessInstanceStatus.RUNNING);
  Page<ProcessInstance> processInstancePage=processRuntime.processInstances(Pageable.of(0,50));
  assertThat(processInstancePage).isNotNull();
  assertThat(processInstancePage.getContent()).hasSize(1);
  ProcessInstance deletedProcessInstance=processRuntime.delete(ProcessPayloadBuilder.delete(categorizeProcess));
  assertThat(deletedProcessInstance).isNotNull();
  assertThat(deletedProcessInstance.getStatus()).isEqualTo(ProcessInstance.ProcessInstanceStatus.DELETED);
  processInstancePage=processRuntime.processInstances(Pageable.of(0,50));
  assertThat(processInstancePage).isNotNull();
  assertThat(processInstancePage.getContent()).hasSize(0);
}
