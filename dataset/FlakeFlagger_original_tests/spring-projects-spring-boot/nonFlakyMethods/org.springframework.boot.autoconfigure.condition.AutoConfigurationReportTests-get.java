@Test public void get() throws Exception {
  assertThat(this.report,not(nullValue()));
  assertThat(this.report,sameInstance(ConditionEvaluationReport.get(this.beanFactory)));
}
