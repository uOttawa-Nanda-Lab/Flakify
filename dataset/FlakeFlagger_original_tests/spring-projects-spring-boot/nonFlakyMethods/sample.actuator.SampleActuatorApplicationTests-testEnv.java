@Test public void testEnv() throws Exception {
  @SuppressWarnings("rawtypes") ResponseEntity<Map> entity=new TestRestTemplate("user",getPassword()).getForEntity("http://localhost:" + this.port + "/env",Map.class);
  assertEquals(HttpStatus.OK,entity.getStatusCode());
  @SuppressWarnings("unchecked") Map<String,Object> body=entity.getBody();
  assertTrue("Wrong body: " + body,body.containsKey("systemProperties"));
}
