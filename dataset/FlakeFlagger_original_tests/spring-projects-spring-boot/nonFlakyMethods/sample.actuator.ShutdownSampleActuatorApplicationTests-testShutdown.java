@Test public void testShutdown() throws Exception {
  @SuppressWarnings("rawtypes") ResponseEntity<Map> entity=new TestRestTemplate("user",getPassword()).postForEntity("http://localhost:" + this.port + "/shutdown",null,Map.class);
  assertEquals(HttpStatus.OK,entity.getStatusCode());
  @SuppressWarnings("unchecked") Map<String,Object> body=entity.getBody();
  assertTrue("Wrong body: " + body,((String)body.get("message")).contains("Shutting down"));
}
