@Test public void testMetricsIsSecure() throws Exception {
  @SuppressWarnings("rawtypes") ResponseEntity<Map> entity=new TestRestTemplate().getForEntity("http://localhost:" + this.port + "/metrics",Map.class);
  assertEquals(HttpStatus.UNAUTHORIZED,entity.getStatusCode());
  entity=new TestRestTemplate().getForEntity("http://localhost:" + this.port + "/metrics/",Map.class);
  assertEquals(HttpStatus.UNAUTHORIZED,entity.getStatusCode());
  entity=new TestRestTemplate().getForEntity("http://localhost:" + this.port + "/metrics/foo",Map.class);
  assertEquals(HttpStatus.UNAUTHORIZED,entity.getStatusCode());
  entity=new TestRestTemplate().getForEntity("http://localhost:" + this.port + "/metrics.json",Map.class);
  assertEquals(HttpStatus.UNAUTHORIZED,entity.getStatusCode());
}
