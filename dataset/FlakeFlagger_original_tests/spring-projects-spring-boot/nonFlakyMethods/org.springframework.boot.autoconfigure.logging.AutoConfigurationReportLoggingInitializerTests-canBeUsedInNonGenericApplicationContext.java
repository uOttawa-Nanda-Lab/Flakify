@Test public void canBeUsedInNonGenericApplicationContext() throws Exception {
  AnnotationConfigWebApplicationContext context=new AnnotationConfigWebApplicationContext();
  context.setServletContext(new MockServletContext());
  context.register(Config.class);
  new AutoConfigurationReportLoggingInitializer().initialize(context);
  context.refresh();
  assertNotNull(context.getBean(ConditionEvaluationReport.class));
}
