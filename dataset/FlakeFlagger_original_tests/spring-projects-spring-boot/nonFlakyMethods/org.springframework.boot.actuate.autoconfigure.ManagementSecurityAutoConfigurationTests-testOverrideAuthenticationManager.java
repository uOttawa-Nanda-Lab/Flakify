@Test public void testOverrideAuthenticationManager() throws Exception {
  this.context=new AnnotationConfigWebApplicationContext();
  this.context.setServletContext(new MockServletContext());
  this.context.register(TestConfiguration.class,SecurityAutoConfiguration.class,ManagementSecurityAutoConfiguration.class,EndpointAutoConfiguration.class,ManagementServerPropertiesAutoConfiguration.class,PropertyPlaceholderAutoConfiguration.class);
  this.context.refresh();
  assertEquals(this.context.getBean(TestConfiguration.class).authenticationManager,this.context.getBean(AuthenticationManager.class));
}
