@Test public void testTaggedWithLevel(){
  MetricName name=MetricName.build("test").level(MetricLevel.CRITICAL);
  MetricName tagged=name.tag("foo","bar");
  Assert.assertEquals(tagged.getMetricLevel(),MetricLevel.CRITICAL);
}
