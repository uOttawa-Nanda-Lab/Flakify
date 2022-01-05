@Test public void recordsKnown404HttpInteractionsAsSingleMetricWithPathAndTemplateVariable() throws Exception {
  AnnotationConfigApplicationContext context=new AnnotationConfigApplicationContext(Config.class,MetricFilterAutoConfiguration.class);
  Filter filter=context.getBean(Filter.class);
  MockMvc mvc=MockMvcBuilders.standaloneSetup(new MetricFilterTestController()).addFilter(filter).build();
  mvc.perform(get("/knownPath/foo")).andExpect(status().isNotFound());
  verify(context.getBean(CounterService.class)).increment("status.404.knownPath.someVariable");
  verify(context.getBean(GaugeService.class)).submit(eq("response.knownPath.someVariable"),anyDouble());
  context.close();
}
