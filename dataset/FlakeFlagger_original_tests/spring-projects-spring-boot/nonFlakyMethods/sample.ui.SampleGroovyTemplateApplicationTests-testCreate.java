@Test public void testCreate() throws Exception {
  MultiValueMap<String,String> map=new LinkedMultiValueMap<String,String>();
  map.set("text","FOO text");
  map.set("summary","FOO");
  URI location=new TestRestTemplate().postForLocation("http://localhost:" + this.port,map);
  assertTrue("Wrong location:\n" + location,location.toString().contains("localhost:" + this.port));
}
