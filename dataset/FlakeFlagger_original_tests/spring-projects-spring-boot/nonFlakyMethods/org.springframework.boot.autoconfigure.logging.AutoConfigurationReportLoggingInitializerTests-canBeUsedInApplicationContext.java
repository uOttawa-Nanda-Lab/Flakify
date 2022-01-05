@Test public void canBeUsedInApplicationContext() throws Exception {
  AnnotationConfigApplicationContext context=new AnnotationConfigApplicationContext();
  context.register(Config.class);
  new AutoConfigurationReportLoggingInitializer().initialize(context);
  context.refresh();
  assertNotNull(context.getBean(ConditionEvaluationReport.class));
}
