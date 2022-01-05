@Test @WithUserDetails(value="salaboy",userDetailsServiceBeanName="myUserDetailsService") public void shouldGetConfiguration(){
  ProcessRuntimeConfiguration configuration=processRuntime.configuration();
  assertThat(configuration).isNotNull();
}
