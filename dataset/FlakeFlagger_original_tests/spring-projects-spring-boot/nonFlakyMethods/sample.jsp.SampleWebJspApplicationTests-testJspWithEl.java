@Test public void testJspWithEl() throws Exception {
  ResponseEntity<String> entity=new TestRestTemplate().getForEntity("http://localhost:" + this.port,String.class);
  assertEquals(HttpStatus.OK,entity.getStatusCode());
  assertTrue("Wrong body:\n" + entity.getBody(),entity.getBody().contains("/resources/text.txt"));
}
