@Test public void testHomeIsSecure() throws Exception {
  ResponseEntity<String> entity=new TestRestTemplate().getForEntity("http://localhost:" + this.port,String.class);
  assertEquals(HttpStatus.UNAUTHORIZED,entity.getStatusCode());
}
