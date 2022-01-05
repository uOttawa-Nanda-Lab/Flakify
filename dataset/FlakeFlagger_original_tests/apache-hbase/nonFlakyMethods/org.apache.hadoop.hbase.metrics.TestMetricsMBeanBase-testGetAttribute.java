public void testGetAttribute() throws Exception {
  this.metricsRate.inc(2);
  this.metricsRate.pushMetric(this.metricsRecord);
  this.intValue.set(5);
  this.intValue.pushMetric(this.metricsRecord);
  this.varyRate.inc(10);
  this.varyRate.inc(50);
  this.varyRate.pushMetric(this.metricsRecord);
  assertEquals(2.0,(Float)this.stats.getAttribute("metricsRate"),0.001);
  assertEquals(5,this.stats.getAttribute("intValue"));
  assertEquals(10L,this.stats.getAttribute("varyRateMinTime"));
  assertEquals(50L,this.stats.getAttribute("varyRateMaxTime"));
  assertEquals(30L,this.stats.getAttribute("varyRateAvgTime"));
  assertEquals(2,this.stats.getAttribute("varyRateNumOps"));
}
