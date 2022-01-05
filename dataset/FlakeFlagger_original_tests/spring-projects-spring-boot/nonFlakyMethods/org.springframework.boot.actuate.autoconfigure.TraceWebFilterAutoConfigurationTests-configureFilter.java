@Test public void configureFilter(){
  AnnotationConfigApplicationContext context=new AnnotationConfigApplicationContext(PropertyPlaceholderAutoConfiguration.class,TraceRepositoryAutoConfiguration.class,TraceWebFilterAutoConfiguration.class);
  assertNotNull(context.getBean(WebRequestTraceFilter.class));
  context.close();
}
