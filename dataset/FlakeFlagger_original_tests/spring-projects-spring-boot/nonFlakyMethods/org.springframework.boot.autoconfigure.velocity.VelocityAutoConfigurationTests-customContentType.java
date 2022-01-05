@Test public void customContentType() throws Exception {
  registerAndRefreshContext("spring.velocity.contentType:application/json");
  MockHttpServletResponse response=render("home");
  String result=response.getContentAsString();
  assertThat(result,containsString("home"));
  assertThat(response.getContentType(),equalTo("application/json;charset=UTF-8"));
}
