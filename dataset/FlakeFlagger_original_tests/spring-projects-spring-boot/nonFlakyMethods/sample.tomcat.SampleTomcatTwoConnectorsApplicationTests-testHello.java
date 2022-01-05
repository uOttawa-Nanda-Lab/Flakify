@Test public void testHello() throws Exception {
  RestTemplate template=new RestTemplate();
  final MySimpleClientHttpRequestFactory factory=new MySimpleClientHttpRequestFactory(new HostnameVerifier(){
    @Override public boolean verify(    final String hostname,    final SSLSession session){
      return true;
    }
  }
);
  template.setRequestFactory(factory);
  ResponseEntity<String> entity=template.getForEntity("http://localhost:" + this.port + "/hello",String.class);
  assertEquals(HttpStatus.OK,entity.getStatusCode());
  assertEquals("hello",entity.getBody());
  ResponseEntity<String> httpsEntity=template.getForEntity("https://localhost:" + this.context.getBean("port") + "/hello",String.class);
  assertEquals(HttpStatus.OK,httpsEntity.getStatusCode());
  assertEquals("hello",httpsEntity.getBody());
}
