@Test @SuppressWarnings("resource") public void springBootConditionPopulatesReport() throws Exception {
  ConditionEvaluationReport report=ConditionEvaluationReport.get(new AnnotationConfigApplicationContext(Config.class).getBeanFactory());
  assertThat(report.getConditionAndOutcomesBySource().size(),not(equalTo(0)));
}
