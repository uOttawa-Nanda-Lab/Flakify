@Test public void testDisableBasicAuthOnApplicationPaths() throws Exception {
  this.context=new AnnotationConfigWebApplicationContext();
  this.context.setServletContext(new MockServletContext());
  this.context.register(HttpMessageConvertersAutoConfiguration.class,EndpointAutoConfiguration.class,EndpointWebMvcAutoConfiguration.class,ManagementServerPropertiesAutoConfiguration.class,SecurityAutoConfiguration.class,ManagementSecurityAutoConfiguration.class,FallbackWebSecurityAutoConfiguration.class,PropertyPlaceholderAutoConfiguration.class);
  EnvironmentTestUtils.addEnvironment(this.context,"security.basic.enabled:false");
  this.context.refresh();
  assertEquals(6,this.context.getBean(FilterChainProxy.class).getFilterChains().size());
}
