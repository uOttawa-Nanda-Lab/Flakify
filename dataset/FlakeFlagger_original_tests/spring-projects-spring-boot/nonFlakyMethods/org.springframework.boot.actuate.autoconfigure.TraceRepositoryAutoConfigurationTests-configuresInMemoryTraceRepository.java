@Test public void configuresInMemoryTraceRepository() throws Exception {
  AnnotationConfigApplicationContext context=new AnnotationConfigApplicationContext(TraceRepositoryAutoConfiguration.class);
  assertNotNull(context.getBean(InMemoryTraceRepository.class));
  context.close();
}
