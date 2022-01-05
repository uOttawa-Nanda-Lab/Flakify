@Test public void testOpenEntityManagerInViewInterceptorNotRegisteredWhenExplicitlyOff() throws Exception {
  AnnotationConfigWebApplicationContext context=new AnnotationConfigWebApplicationContext();
  EnvironmentTestUtils.addEnvironment(context,"spring.jpa.open_in_view:false");
  context.register(TestConfiguration.class,EmbeddedDataSourceConfiguration.class,PropertyPlaceholderAutoConfiguration.class,getAutoConfigureClass());
  context.refresh();
  assertEquals(0,getInterceptorBeans(context).length);
  context.close();
}
