@Test public void testNOPCompass(){
  Compass compass=MetricManager.getCompass("test",MetricName.build("test"));
  compass.record(10,"success");
  Assert.assertEquals(0,compass.getCountAndRtPerCategory().size());
  Assert.assertEquals(0,compass.getMethodCountPerCategory().size());
  Assert.assertEquals(0,compass.getMethodRtPerCategory().size());
}
