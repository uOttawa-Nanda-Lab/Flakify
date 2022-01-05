@Test public void testResolveWithoutTags(){
  final MetricName name=new MetricName("foo").tag("key","value");
  Assert.assertEquals(name.resolve("bar",false),new MetricName("foo.bar"));
}
