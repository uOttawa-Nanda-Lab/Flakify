@Test public void testDisableIgnoredStaticApplicationPaths() throws Exception {
  this.context=new AnnotationConfigWebApplicationContext();
  this.context.setServletContext(new MockServletContext());
  this.context.register(SecurityAutoConfiguration.class,ServerPropertiesAutoConfiguration.class,PropertyPlaceholderAutoConfiguration.class);
  EnvironmentTestUtils.addEnvironment(this.context,"security.ignored:none");
  this.context.refresh();
  assertEquals(1,this.context.getBean(FilterChainProxy.class).getFilterChains().size());
}
