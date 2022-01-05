@Test public void multipleDataSourcesWithPrimary(){
  load(MultipleDataSourcesWithPrimaryConfig.class);
  PublicMetrics bean=this.context.getBean(PublicMetrics.class);
  Collection<Metric<?>> metrics=bean.metrics();
  assertMetrics(metrics,"datasource.primary.active","datasource.primary.usage","datasource.commonsDbcp.active","datasource.commonsDbcp.usage");
}
