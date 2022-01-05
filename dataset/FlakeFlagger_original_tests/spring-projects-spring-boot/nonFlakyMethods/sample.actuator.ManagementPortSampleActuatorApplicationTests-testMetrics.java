@Test public void testMetrics() throws Exception {
  testHome();
  @SuppressWarnings("rawtypes") ResponseEntity<Map> entity=new TestRestTemplate().getForEntity("http://localhost:" + this.managementPort + "/metrics",Map.class);
  assertEquals(HttpStatus.UNAUTHORIZED,entity.getStatusCode());
}
