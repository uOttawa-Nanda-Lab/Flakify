@Test public void testMetrics() throws Exception {
  @SuppressWarnings("rawtypes") ResponseEntity<Map> entity=new TestRestTemplate().getForEntity("http://localhost:" + this.port + "/metrics",Map.class);
  assertEquals(HttpStatus.UNAUTHORIZED,entity.getStatusCode());
}
