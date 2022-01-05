@Test public void envEndpointNotHidden(){
  String body=new TestRestTemplate().getForObject("http://localhost:" + this.port + "/env/user.dir",String.class);
  assertNotNull(body);
  assertTrue("Wrong body: \n" + body,body.contains("spring-boot-actuator"));
}
