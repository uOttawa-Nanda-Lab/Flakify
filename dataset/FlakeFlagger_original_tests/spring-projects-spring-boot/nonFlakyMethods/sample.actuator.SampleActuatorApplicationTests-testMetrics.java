@Test public void testMetrics() throws Exception {
  testHome();
  @SuppressWarnings("rawtypes") ResponseEntity<Map> entity=new TestRestTemplate("user",getPassword()).getForEntity("http://localhost:" + this.port + "/metrics",Map.class);
  assertEquals(HttpStatus.OK,entity.getStatusCode());
  @SuppressWarnings("unchecked") Map<String,Object> body=entity.getBody();
  assertTrue("Wrong body: " + body,body.containsKey("counter.status.200.root"));
}
