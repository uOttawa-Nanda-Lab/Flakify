@Test @WithUserDetails(value="salaboy",userDetailsServiceBeanName="myUserDetailsService") public void shouldGetAvailableProcessDefinitionForTheGivenUser(){
  Page<ProcessDefinition> processDefinitionPage=processRuntime.processDefinitions(Pageable.of(0,50));
  assertThat(processDefinitionPage.getContent()).isNotNull();
  assertThat(processDefinitionPage.getContent()).extracting(ProcessDefinition::getKey).contains(CATEGORIZE_PROCESS,CATEGORIZE_HUMAN_PROCESS);
}
