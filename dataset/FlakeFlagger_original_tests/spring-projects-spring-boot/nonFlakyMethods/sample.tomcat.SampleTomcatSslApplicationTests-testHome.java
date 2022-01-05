@Test public void testHome() throws Exception {
  SSLConnectionSocketFactory socketFactory=new SSLConnectionSocketFactory(new SSLContextBuilder().loadTrustMaterial(null,new TrustSelfSignedStrategy()).build());
  HttpClient httpClient=HttpClients.custom().setSSLSocketFactory(socketFactory).build();
  TestRestTemplate testRestTemplate=new TestRestTemplate();
  ((HttpComponentsClientHttpRequestFactory)testRestTemplate.getRequestFactory()).setHttpClient(httpClient);
  ResponseEntity<String> entity=testRestTemplate.getForEntity("https://localhost:" + this.port,String.class);
  assertEquals(HttpStatus.OK,entity.getStatusCode());
  assertEquals("Hello, world",entity.getBody());
}
