@Test public void testHome() throws Exception {
  @SuppressWarnings("rawtypes") ResponseEntity<Map> entity=new TestRestTemplate("user",getPassword()).getForEntity("http://localhost:" + this.port,Map.class);
  assertEquals(HttpStatus.OK,entity.getStatusCode());
  @SuppressWarnings("unchecked") Map<String,Object> body=entity.getBody();
  assertEquals("Hello Phil",body.get("message"));
}
