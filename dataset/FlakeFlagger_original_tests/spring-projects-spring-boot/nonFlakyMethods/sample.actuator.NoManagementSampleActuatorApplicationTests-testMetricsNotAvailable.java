@Test public void testMetricsNotAvailable() throws Exception {
  testHome();
  @SuppressWarnings("rawtypes") ResponseEntity<Map> entity=new TestRestTemplate("user",getPassword()).getForEntity("http://localhost:" + this.port + "/metrics",Map.class);
  assertEquals(HttpStatus.NOT_FOUND,entity.getStatusCode());
}
