@Test public void testWebConfigurationInjectGlobalAuthentication() throws Exception {
  this.context=SpringApplication.run(TestInjectWebConfiguration.class,"--server.port=0","--debug");
  assertNotNull(this.context.getBean(AuthenticationManagerBuilder.class));
  assertNotNull(this.context.getBean(AuthenticationManager.class).authenticate(new UsernamePasswordAuthenticationToken("dave","secret")));
}
