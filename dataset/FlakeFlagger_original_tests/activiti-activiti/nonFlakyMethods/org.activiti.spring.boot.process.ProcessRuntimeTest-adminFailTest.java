@Test(expected=AccessDeniedException.class) @WithUserDetails(value="salaboy",userDetailsServiceBeanName="myUserDetailsService") public void adminFailTest(){
  ProcessInstance fakeId=processAdminRuntime.processInstance("fakeId");
}
