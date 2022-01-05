@Test public void testResolveToEmpty(){
  final MetricName name=new MetricName("foo");
  Assert.assertEquals(name.resolve(null),new MetricName("foo"));
}
