@Test public void testDisableBasicAuthOnApplicationPaths() throws Exception {
  this.context=new AnnotationConfigWebApplicationContext();
  this.context.setServletContext(new MockServletContext());
  this.context.register(SecurityAutoConfiguration.class,ServerPropertiesAutoConfiguration.class,PropertyPlaceholderAutoConfiguration.class);
  EnvironmentTestUtils.addEnvironment(this.context,"security.basic.enabled:false");
  this.context.refresh();
  assertEquals(1,this.context.getBeanNamesForType(FilterChainProxy.class).length);
}
