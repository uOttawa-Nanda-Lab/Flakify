@Test public void testResolveBothEmpty(){
  final MetricName name=new MetricName(null);
  Assert.assertEquals(name.resolve(null),new MetricName());
}
