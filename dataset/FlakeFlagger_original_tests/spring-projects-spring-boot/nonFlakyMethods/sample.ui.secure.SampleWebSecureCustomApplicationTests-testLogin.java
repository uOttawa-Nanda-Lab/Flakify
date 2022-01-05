@Test public void testLogin() throws Exception {
  HttpHeaders headers=getHeaders();
  headers.setAccept(Arrays.asList(MediaType.TEXT_HTML));
  headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
  MultiValueMap<String,String> form=new LinkedMultiValueMap<String,String>();
  form.set("username","user");
  form.set("password","user");
  ResponseEntity<String> entity=new TestRestTemplate().exchange("http://localhost:" + this.port + "/login",HttpMethod.POST,new HttpEntity<MultiValueMap<String,String>>(form,headers),String.class);
  assertEquals(HttpStatus.FOUND,entity.getStatusCode());
  assertTrue("Wrong location:\n" + entity.getHeaders(),entity.getHeaders().getLocation().toString().endsWith(port + "/"));
  assertNotNull("Missing cookie:\n" + entity.getHeaders(),entity.getHeaders().get("Set-Cookie"));
}
