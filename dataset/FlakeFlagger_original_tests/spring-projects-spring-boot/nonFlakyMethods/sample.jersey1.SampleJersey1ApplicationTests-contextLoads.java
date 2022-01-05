@Test public void contextLoads(){
  assertEquals("Hello World",new TestRestTemplate().getForObject("http://localhost:" + this.port + "/",String.class));
}
