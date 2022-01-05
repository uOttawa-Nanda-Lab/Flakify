@Test public void testJoinWithLevel(){
  MetricName name=MetricName.build("test").level(MetricLevel.CRITICAL);
  MetricName tagged=MetricName.join(name,MetricName.build("abc"));
  Assert.assertEquals(tagged.getMetricLevel(),MetricLevel.CRITICAL);
}
