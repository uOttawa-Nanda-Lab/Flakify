@Test public void testFreeMarkerTemplate() throws Exception {
  ResponseEntity<String> entity=new TestRestTemplate().getForEntity("http://localhost:" + port,String.class);
  assertEquals(HttpStatus.OK,entity.getStatusCode());
  assertTrue("Wrong body:\n" + entity.getBody(),entity.getBody().contains("Hello, Andy"));
}
