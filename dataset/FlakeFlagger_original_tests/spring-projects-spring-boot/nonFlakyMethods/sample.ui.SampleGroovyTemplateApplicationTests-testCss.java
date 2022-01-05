@Test public void testCss() throws Exception {
  ResponseEntity<String> entity=new TestRestTemplate().getForEntity("http://localhost:" + this.port + "/css/bootstrap.min.css",String.class);
  assertEquals(HttpStatus.OK,entity.getStatusCode());
  assertTrue("Wrong body:\n" + entity.getBody(),entity.getBody().contains("body"));
}
