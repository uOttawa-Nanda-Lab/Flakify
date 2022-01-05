@Test public void testBucketCounter(){
  BucketCounter bc=MetricManager.getBucketCounters("test",MetricName.build("test3"));
  bc.update();
  Assert.assertEquals(0,bc.getBucketInterval());
  Assert.assertEquals(0,bc.getBucketCounts().size());
}
