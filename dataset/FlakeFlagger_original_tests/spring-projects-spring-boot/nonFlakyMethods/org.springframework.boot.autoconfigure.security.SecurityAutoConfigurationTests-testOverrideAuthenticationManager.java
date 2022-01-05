@Test public void testOverrideAuthenticationManager() throws Exception {
  this.context=new AnnotationConfigWebApplicationContext();
  this.context.setServletContext(new MockServletContext());
  this.context.register(TestAuthenticationConfiguration.class,SecurityAutoConfiguration.class,ServerPropertiesAutoConfiguration.class,PropertyPlaceholderAutoConfiguration.class);
  this.context.refresh();
  assertEquals(this.context.getBean(TestAuthenticationConfiguration.class).authenticationManager,this.context.getBean(AuthenticationManager.class));
}
