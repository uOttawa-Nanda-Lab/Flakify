@Test public void setPrependsGauge(){
  this.service.submit("foo",2.3);
  @SuppressWarnings("rawtypes") ArgumentCaptor<Metric> captor=ArgumentCaptor.forClass(Metric.class);
  verify(this.repository).set(captor.capture());
  assertEquals("gauge.foo",captor.getValue().getName());
  assertEquals(2.3,captor.getValue().getValue());
}
