@Test public void skipsIfRepositoryExists() throws Exception {
  AnnotationConfigApplicationContext context=new AnnotationConfigApplicationContext(Config.class,TraceRepositoryAutoConfiguration.class);
  assertThat(context.getBeansOfType(InMemoryTraceRepository.class).size(),equalTo(0));
  assertThat(context.getBeansOfType(TraceRepository.class).size(),equalTo(1));
  context.close();
}
