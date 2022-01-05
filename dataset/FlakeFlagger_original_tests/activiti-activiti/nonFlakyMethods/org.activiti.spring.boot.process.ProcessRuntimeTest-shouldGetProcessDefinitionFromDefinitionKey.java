@Test @WithUserDetails(value="salaboy",userDetailsServiceBeanName="myUserDetailsService") public void shouldGetProcessDefinitionFromDefinitionKey(){
  ProcessDefinition categorizeHumanProcess=processRuntime.processDefinition(CATEGORIZE_HUMAN_PROCESS);
  assertThat(categorizeHumanProcess).isNotNull();
  assertThat(categorizeHumanProcess.getName()).isEqualTo(CATEGORIZE_HUMAN_PROCESS);
  assertThat(categorizeHumanProcess.getId()).contains(CATEGORIZE_HUMAN_PROCESS);
}
