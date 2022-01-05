@Test public void testWebConfigurationOverrideGlobalAuthentication() throws Exception {
  this.context=SpringApplication.run(TestWebConfiguration.class,"--server.port=0","--debug");
  assertNotNull(this.context.getBean(AuthenticationManagerBuilder.class));
  assertNotNull(this.context.getBean(AuthenticationManager.class).authenticate(new UsernamePasswordAuthenticationToken("dave","secret")));
}
