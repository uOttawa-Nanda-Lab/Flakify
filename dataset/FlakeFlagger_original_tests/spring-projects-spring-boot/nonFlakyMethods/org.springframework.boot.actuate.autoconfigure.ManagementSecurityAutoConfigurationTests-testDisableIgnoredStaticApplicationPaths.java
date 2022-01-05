@Test public void testDisableIgnoredStaticApplicationPaths() throws Exception {
  this.context=new AnnotationConfigWebApplicationContext();
  this.context.setServletContext(new MockServletContext());
  this.context.register(SecurityAutoConfiguration.class,ManagementSecurityAutoConfiguration.class,EndpointAutoConfiguration.class,ManagementServerPropertiesAutoConfiguration.class,PropertyPlaceholderAutoConfiguration.class);
  EnvironmentTestUtils.addEnvironment(this.context,"security.ignored:none");
  this.context.refresh();
  assertEquals(2,this.context.getBean(FilterChainProxy.class).getFilterChains().size());
}
