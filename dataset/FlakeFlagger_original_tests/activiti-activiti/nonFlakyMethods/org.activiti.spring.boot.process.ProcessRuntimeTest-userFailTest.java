@Test(expected=AccessDeniedException.class) @WithUserDetails(value="admin",userDetailsServiceBeanName="myUserDetailsService") public void userFailTest(){
  Page<ProcessDefinition> processDefinitionPage=processRuntime.processDefinitions(Pageable.of(0,50));
}
