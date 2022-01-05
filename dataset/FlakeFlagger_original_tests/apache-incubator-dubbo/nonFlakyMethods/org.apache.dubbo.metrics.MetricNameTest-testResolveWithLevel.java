@Test public void testResolveWithLevel(){
  final MetricName name=new MetricName("foo").level(MetricLevel.CRITICAL).tag("key","value");
  Assert.assertEquals(name.resolve("bar"),new MetricName("foo.bar").tag("key","value").level(MetricLevel.CRITICAL));
}
