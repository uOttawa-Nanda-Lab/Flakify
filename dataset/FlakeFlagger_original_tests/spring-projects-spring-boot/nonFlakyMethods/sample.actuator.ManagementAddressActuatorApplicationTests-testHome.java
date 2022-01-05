@Test public void testHome() throws Exception {
  @SuppressWarnings("rawtypes") ResponseEntity<Map> entity=new TestRestTemplate().getForEntity("http://localhost:" + this.port,Map.class);
  assertEquals(HttpStatus.UNAUTHORIZED,entity.getStatusCode());
}
