@Test public void parentBottomUp() throws Exception {
  this.beanFactory=new DefaultListableBeanFactory();
  this.beanFactory.setParentBeanFactory(new DefaultListableBeanFactory());
  ConditionEvaluationReport.get((ConfigurableListableBeanFactory)this.beanFactory.getParentBeanFactory());
  this.report=ConditionEvaluationReport.get(this.beanFactory);
  assertThat(this.report,not(nullValue()));
  assertThat(this.report,not(sameInstance(this.report.getParent())));
  assertThat(this.report.getParent(),not(nullValue()));
  assertThat(this.report.getParent().getParent(),nullValue());
}
