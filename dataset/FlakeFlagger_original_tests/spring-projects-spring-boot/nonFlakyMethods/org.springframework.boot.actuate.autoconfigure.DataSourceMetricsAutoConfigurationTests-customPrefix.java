@Test public void customPrefix(){
  load(MultipleDataSourcesWithPrimaryConfig.class,CustomDataSourcePublicMetrics.class);
  PublicMetrics bean=this.context.getBean(PublicMetrics.class);
  Collection<Metric<?>> metrics=bean.metrics();
  assertMetrics(metrics,"ds.first.active","ds.first.usage","ds.second.active","ds.second.usage");
}
