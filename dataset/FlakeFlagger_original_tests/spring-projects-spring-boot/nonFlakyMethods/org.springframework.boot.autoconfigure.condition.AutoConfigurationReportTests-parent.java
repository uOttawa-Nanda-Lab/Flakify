@Test public void parent() throws Exception {
  this.beanFactory.setParentBeanFactory(new DefaultListableBeanFactory());
  ConditionEvaluationReport.get((ConfigurableListableBeanFactory)this.beanFactory.getParentBeanFactory());
  assertThat(this.report,sameInstance(ConditionEvaluationReport.get(this.beanFactory)));
  assertThat(this.report,not(nullValue()));
  assertThat(this.report.getParent(),not(nullValue()));
  ConditionEvaluationReport.get((ConfigurableListableBeanFactory)this.beanFactory.getParentBeanFactory());
  assertThat(this.report,sameInstance(ConditionEvaluationReport.get(this.beanFactory)));
  assertThat(this.report.getParent(),sameInstance(ConditionEvaluationReport.get((ConfigurableListableBeanFactory)this.beanFactory.getParentBeanFactory())));
}
