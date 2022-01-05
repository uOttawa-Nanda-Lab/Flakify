@Test public void recordsHttpInteractionsWithTemplateVariable() throws Exception {
  AnnotationConfigApplicationContext context=new AnnotationConfigApplicationContext(Config.class,MetricFilterAutoConfiguration.class);
  Filter filter=context.getBean(Filter.class);
  MockMvc mvc=MockMvcBuilders.standaloneSetup(new MetricFilterTestController()).addFilter(filter).build();
  mvc.perform(get("/templateVarTest/foo")).andExpect(status().isOk());
  verify(context.getBean(CounterService.class)).increment("status.200.templateVarTest.someVariable");
  verify(context.getBean(GaugeService.class)).submit(eq("response.templateVarTest.someVariable"),anyDouble());
  context.close();
}
