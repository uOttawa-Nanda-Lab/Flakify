@Test public void skipsFilterIfMissingServices() throws Exception {
  AnnotationConfigApplicationContext context=new AnnotationConfigApplicationContext(MetricFilterAutoConfiguration.class);
  assertThat(context.getBeansOfType(Filter.class).size(),equalTo(0));
  context.close();
}
