@Test public void testManagementProtected() throws Exception {
  ResponseEntity<String> entity=new TestRestTemplate().getForEntity("http://localhost:" + this.port + "/beans",String.class);
  assertEquals(HttpStatus.UNAUTHORIZED,entity.getStatusCode());
}
