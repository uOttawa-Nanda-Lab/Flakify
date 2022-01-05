@Test @WithUserDetails(value="admin",userDetailsServiceBeanName="myUserDetailsService") public void validatingConfigurationForAdmin(){
  String authenticatedUserId=securityManager.getAuthenticatedUserId();
  assertThat(authenticatedUserId).isNotBlank();
  UserDetails userDetails=userDetailsService.loadUserByUsername(authenticatedUserId);
  assertThat(userDetails).isNotNull();
  assertThat(userDetails.getAuthorities()).hasSize(1);
  List<String> userRoles=userGroupManager.getUserRoles(authenticatedUserId);
  assertThat(userRoles).isNotNull();
  assertThat(userRoles).hasSize(1);
  assertThat(userRoles.get(0)).isEqualTo("ACTIVITI_ADMIN");
  List<String> userGroups=userGroupManager.getUserGroups(authenticatedUserId);
  assertThat(userGroups).isNotNull();
  assertThat(userGroups).hasSize(0);
}
