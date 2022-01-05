@Test public void autoDataSource(){
  load(DataSourceAutoConfiguration.class);
  PublicMetrics bean=this.context.getBean(PublicMetrics.class);
  Collection<Metric<?>> metrics=bean.metrics();
  assertMetrics(metrics,"datasource.primary.active","datasource.primary.usage");
}
