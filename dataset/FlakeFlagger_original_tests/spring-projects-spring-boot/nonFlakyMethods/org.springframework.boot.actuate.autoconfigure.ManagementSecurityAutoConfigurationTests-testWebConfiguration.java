@Test public void testWebConfiguration() throws Exception {
  this.context=new AnnotationConfigWebApplicationContext();
  this.context.setServletContext(new MockServletContext());
  this.context.register(SecurityAutoConfiguration.class,ManagementSecurityAutoConfiguration.class,HttpMessageConvertersAutoConfiguration.class,EndpointAutoConfiguration.class,EndpointWebMvcAutoConfiguration.class,ManagementServerPropertiesAutoConfiguration.class,PropertyPlaceholderAutoConfiguration.class);
  this.context.refresh();
  assertNotNull(this.context.getBean(AuthenticationManagerBuilder.class));
  assertEquals(6,this.context.getBean(FilterChainProxy.class).getFilterChains().size());
}
