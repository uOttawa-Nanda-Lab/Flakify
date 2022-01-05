@Test public void testEmpty(){
  Assert.assertEquals(MetricName.EMPTY.getTags(),MetricName.EMPTY_TAGS);
  Assert.assertNull(MetricName.EMPTY.getKey());
  Assert.assertEquals(new MetricName().getTags(),MetricName.EMPTY_TAGS);
  Assert.assertEquals(MetricName.EMPTY,new MetricName());
  Assert.assertEquals(MetricName.build(),MetricName.EMPTY);
  Assert.assertEquals(MetricName.EMPTY.resolve(null),MetricName.EMPTY);
}
