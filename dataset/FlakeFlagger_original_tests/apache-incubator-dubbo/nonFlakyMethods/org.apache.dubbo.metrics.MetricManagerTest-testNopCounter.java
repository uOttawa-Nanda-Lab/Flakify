@Test public void testNopCounter(){
  Counter counter=MetricManager.getCounter("test",MetricName.build("test2"));
  counter.inc();
  Assert.assertEquals(0,counter.getCount());
}
