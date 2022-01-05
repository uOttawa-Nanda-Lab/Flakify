@Test public void testOpenEntityManagerInViewInterceptorCreated() throws Exception {
  AnnotationConfigWebApplicationContext context=new AnnotationConfigWebApplicationContext();
  context.register(TestConfiguration.class,EmbeddedDataSourceConfiguration.class,PropertyPlaceholderAutoConfiguration.class,getAutoConfigureClass());
  context.refresh();
  assertNotNull(context.getBean(OpenEntityManagerInViewInterceptor.class));
  context.close();
}
