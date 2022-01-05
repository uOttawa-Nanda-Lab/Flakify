@Test public void testSecureHealth() throws Exception {
  ResponseEntity<String> entity=new TestRestTemplate("user",getPassword()).getForEntity("http://localhost:" + this.port + "/health",String.class);
  assertEquals(HttpStatus.OK,entity.getStatusCode());
  assertTrue("Wrong body: " + entity.getBody(),entity.getBody().contains("\"hello\":1"));
}
