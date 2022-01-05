@Test public void testHealth() throws Exception {
  ResponseEntity<String> entity=new TestRestTemplate().getForEntity("http://localhost:" + this.managementPort + "/health",String.class);
  assertEquals(HttpStatus.OK,entity.getStatusCode());
  assertEquals("{\"status\":\"UP\"}",entity.getBody());
}
