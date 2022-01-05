@Test public void testErrorPageDirectAccess() throws Exception {
  @SuppressWarnings("rawtypes") ResponseEntity<Map> entity=new TestRestTemplate().getForEntity("http://localhost:" + this.port + "/error",Map.class);
  assertEquals(HttpStatus.INTERNAL_SERVER_ERROR,entity.getStatusCode());
  @SuppressWarnings("unchecked") Map<String,Object> body=entity.getBody();
  assertEquals("None",body.get("error"));
  assertEquals(999,body.get("status"));
}
