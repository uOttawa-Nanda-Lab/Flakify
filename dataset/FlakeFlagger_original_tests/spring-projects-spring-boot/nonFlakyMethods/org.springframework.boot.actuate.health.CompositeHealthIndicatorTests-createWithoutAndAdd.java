@Test public void createWithoutAndAdd() throws Exception {
  CompositeHealthIndicator composite=new CompositeHealthIndicator(this.healthAggregator);
  composite.addHealthIndicator("one",this.one);
  composite.addHealthIndicator("two",this.two);
  Health result=composite.health();
  assertThat(result.getDetails().size(),equalTo(2));
  assertThat(result.getDetails(),hasEntry("one",(Object)new Health.Builder().unknown().withDetail("1","1").build()));
  assertThat(result.getDetails(),hasEntry("two",(Object)new Health.Builder().unknown().withDetail("2","2").build()));
}
