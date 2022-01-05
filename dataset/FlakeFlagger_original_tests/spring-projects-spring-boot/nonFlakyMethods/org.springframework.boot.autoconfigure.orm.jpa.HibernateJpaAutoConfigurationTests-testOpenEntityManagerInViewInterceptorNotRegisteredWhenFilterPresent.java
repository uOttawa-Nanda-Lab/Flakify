@Test public void testOpenEntityManagerInViewInterceptorNotRegisteredWhenFilterPresent() throws Exception {
  AnnotationConfigWebApplicationContext context=new AnnotationConfigWebApplicationContext();
  context.register(TestFilterConfiguration.class,EmbeddedDataSourceConfiguration.class,PropertyPlaceholderAutoConfiguration.class,getAutoConfigureClass());
  context.refresh();
  assertEquals(0,getInterceptorBeans(context).length);
  context.close();
}
