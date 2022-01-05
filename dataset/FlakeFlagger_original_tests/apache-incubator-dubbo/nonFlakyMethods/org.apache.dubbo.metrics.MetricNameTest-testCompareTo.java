@Test public void testCompareTo(){
  final MetricName a=MetricName.EMPTY.tag("foo","bar");
  final MetricName b=MetricName.EMPTY.tag("foo","baz");
  Assert.assertTrue(a.compareTo(b) < 0);
  Assert.assertTrue(b.compareTo(a) > 0);
  Assert.assertTrue(b.compareTo(b) == 0);
  Assert.assertTrue(b.resolve("key").compareTo(b) < 0);
  Assert.assertTrue(b.compareTo(b.resolve("key")) > 0);
}
