@Test public void testResolveWithTags(){
  final MetricName name=new MetricName("foo").tag("key","value");
  Assert.assertEquals(name.resolve("bar"),new MetricName("foo.bar").tag("key","value"));
}
