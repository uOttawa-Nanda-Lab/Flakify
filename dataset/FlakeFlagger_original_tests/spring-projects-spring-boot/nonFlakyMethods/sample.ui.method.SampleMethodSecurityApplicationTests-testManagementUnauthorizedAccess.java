@Test public void testManagementUnauthorizedAccess() throws Exception {
  ResponseEntity<String> entity=new TestRestTemplate("user","user").getForEntity("http://localhost:" + this.port + "/beans",String.class);
  assertEquals(HttpStatus.FORBIDDEN,entity.getStatusCode());
}
