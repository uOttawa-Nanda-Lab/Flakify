@Test public void testWebConfiguration() throws Exception {
  this.context=new AnnotationConfigWebApplicationContext();
  this.context.setServletContext(new MockServletContext());
  this.context.register(SecurityAutoConfiguration.class,ServerPropertiesAutoConfiguration.class,PropertyPlaceholderAutoConfiguration.class);
  this.context.refresh();
  assertNotNull(this.context.getBean(AuthenticationManagerBuilder.class));
  List<SecurityFilterChain> filterChains=this.context.getBean(FilterChainProxy.class).getFilterChains();
  assertEquals(5,filterChains.size());
}
