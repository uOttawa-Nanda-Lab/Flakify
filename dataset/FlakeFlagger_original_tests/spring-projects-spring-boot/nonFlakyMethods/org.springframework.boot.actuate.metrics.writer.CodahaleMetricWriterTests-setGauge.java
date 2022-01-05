@Test public void setGauge(){
  this.writer.set(new Metric<Number>("foo",2.1));
  this.writer.set(new Metric<Number>("foo",2.3));
  @SuppressWarnings("unchecked") Gauge<Double> gauge=(Gauge<Double>)this.registry.getMetrics().get("foo");
  assertEquals(new Double(2.3),gauge.getValue());
}
