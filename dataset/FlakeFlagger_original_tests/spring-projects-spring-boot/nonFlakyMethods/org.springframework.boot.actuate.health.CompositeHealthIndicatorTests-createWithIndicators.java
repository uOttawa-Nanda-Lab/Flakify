@Test public void createWithIndicators() throws Exception {
  Map<String,HealthIndicator> indicators=new HashMap<String,HealthIndicator>();
  indicators.put("one",this.one);
  indicators.put("two",this.two);
  CompositeHealthIndicator composite=new CompositeHealthIndicator(this.healthAggregator,indicators);
  Health result=composite.health();
  assertThat(result.getDetails().size(),equalTo(2));
  assertThat(result.getDetails(),hasEntry("one",(Object)new Health.Builder().unknown().withDetail("1","1").build()));
  assertThat(result.getDetails(),hasEntry("two",(Object)new Health.Builder().unknown().withDetail("2","2").build()));
}
