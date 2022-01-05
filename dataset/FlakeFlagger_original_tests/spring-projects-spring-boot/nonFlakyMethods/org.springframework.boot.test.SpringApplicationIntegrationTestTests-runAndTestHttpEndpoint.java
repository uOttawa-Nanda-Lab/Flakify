@Test public void runAndTestHttpEndpoint(){
  assertNotEquals(8080,this.port);
  assertNotEquals(0,this.port);
  String body=new RestTemplate().getForObject("http://localhost:" + this.port + "/",String.class);
  assertEquals("Hello World",body);
}
