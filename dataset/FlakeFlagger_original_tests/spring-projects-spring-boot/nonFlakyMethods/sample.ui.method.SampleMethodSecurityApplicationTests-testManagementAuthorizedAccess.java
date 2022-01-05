@Test public void testManagementAuthorizedAccess() throws Exception {
  ResponseEntity<String> entity=new TestRestTemplate("admin","admin").getForEntity("http://localhost:" + this.port + "/beans",String.class);
  assertEquals(HttpStatus.OK,entity.getStatusCode());
}
