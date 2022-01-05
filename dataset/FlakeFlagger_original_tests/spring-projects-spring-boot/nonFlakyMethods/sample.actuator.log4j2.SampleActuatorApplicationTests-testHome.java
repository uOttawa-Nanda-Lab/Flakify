@Test public void testHome() throws Exception {
  @SuppressWarnings("rawtypes") ResponseEntity<Map> entity=new TestRestTemplate().getForEntity("http://localhost:" + port,Map.class);
  assertEquals(HttpStatus.OK,entity.getStatusCode());
  @SuppressWarnings("unchecked") Map<String,Object> body=entity.getBody();
  assertEquals("Hello Daniel",body.get("message"));
}
