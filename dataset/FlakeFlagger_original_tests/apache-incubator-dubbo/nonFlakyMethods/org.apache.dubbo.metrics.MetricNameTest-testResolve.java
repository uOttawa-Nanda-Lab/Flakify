@Test public void testResolve(){
  final MetricName name=new MetricName("foo");
  Assert.assertEquals(name.resolve("bar"),new MetricName("foo.bar"));
}
