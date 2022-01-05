@Test @WithUserDetails(value="salaboy",userDetailsServiceBeanName="myUserDetailsService") public void getRestrictedProcessDefs(){
  ProcessRuntimeConfiguration configuration=processRuntime.configuration();
  assertThat(configuration).isNotNull();
  Page<ProcessDefinition> processDefinitionPage=processRuntime.processDefinitions(Pageable.of(0,50));
  assertThat(processDefinitionPage.getContent()).isNotNull();
  assertThat(processDefinitionPage.getContent()).hasSize(2);
}
