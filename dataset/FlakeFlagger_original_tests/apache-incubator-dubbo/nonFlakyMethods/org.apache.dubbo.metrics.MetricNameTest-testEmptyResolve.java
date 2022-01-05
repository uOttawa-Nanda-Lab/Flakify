@Test public void testEmptyResolve(){
  final MetricName name=new MetricName();
  Assert.assertEquals(name.resolve("foo"),new MetricName("foo"));
}
