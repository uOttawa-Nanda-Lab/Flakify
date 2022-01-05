@Test public void records404HttpInteractionsAsSingleMetric() throws Exception {
  AnnotationConfigApplicationContext context=new AnnotationConfigApplicationContext(Config.class,MetricFilterAutoConfiguration.class);
  Filter filter=context.getBean(Filter.class);
  MockMvc mvc=MockMvcBuilders.standaloneSetup(new MetricFilterTestController()).addFilter(filter).build();
  mvc.perform(get("/unknownPath/1")).andExpect(status().isNotFound());
  mvc.perform(get("/unknownPath/2")).andExpect(status().isNotFound());
  verify(context.getBean(CounterService.class),times(2)).increment("status.404.unmapped");
  verify(context.getBean(GaugeService.class),times(2)).submit(eq("response.unmapped"),anyDouble());
  context.close();
}
