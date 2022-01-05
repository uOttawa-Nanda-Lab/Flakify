@Test @WithUserDetails(value="salaboy",userDetailsServiceBeanName="myUserDetailsService") public void validatingConfigurationForUser(){
  String authenticatedUserId=securityManager.getAuthenticatedUserId();
  assertThat(authenticatedUserId).isNotBlank();
  UserDetails userDetails=userDetailsService.loadUserByUsername(authenticatedUserId);
  assertThat(userDetails).isNotNull();
  assertThat(userDetails.getAuthorities()).hasSize(2);
  List<String> userRoles=userGroupManager.getUserRoles(authenticatedUserId);
  assertThat(userRoles).isNotNull();
  assertThat(userRoles).hasSize(1);
  assertThat(userRoles.get(0)).isEqualTo("ACTIVITI_USER");
  List<String> userGroups=userGroupManager.getUserGroups(authenticatedUserId);
  assertThat(userGroups).isNotNull();
  assertThat(userGroups).hasSize(1);
  assertThat(userGroups.get(0)).isEqualTo("activitiTeam");
}
