@Test public void testNOPMetricManager(){
  Assert.assertTrue(MetricManager.getIMetricManager() instanceof NOPMetricManager);
}
