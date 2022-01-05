@Test @WithUserDetails(value="admin",userDetailsServiceBeanName="myUserDetailsService") public void getAllProcessDefsForAdmin(){
  Page<ProcessDefinition> processDefinitionPage=processAdminRuntime.processDefinitions(Pageable.of(0,50));
  assertThat(processDefinitionPage.getContent()).isNotNull();
  assertThat(processDefinitionPage.getContent()).hasSize(4);
}
