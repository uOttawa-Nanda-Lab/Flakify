@Test public void skipsIfBeansExist() throws Exception {
  AnnotationConfigApplicationContext context=new AnnotationConfigApplicationContext(Config.class,MetricRepositoryAutoConfiguration.class);
  assertThat(context.getBeansOfType(DefaultGaugeService.class).size(),equalTo(0));
  assertThat(context.getBeansOfType(DefaultCounterService.class).size(),equalTo(0));
  context.close();
}
