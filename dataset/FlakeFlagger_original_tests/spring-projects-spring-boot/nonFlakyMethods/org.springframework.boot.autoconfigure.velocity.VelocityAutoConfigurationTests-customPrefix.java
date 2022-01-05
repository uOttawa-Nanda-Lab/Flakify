@Test public void customPrefix() throws Exception {
  registerAndRefreshContext("spring.velocity.prefix:prefix/");
  MockHttpServletResponse response=render("prefixed");
  String result=response.getContentAsString();
  assertThat(result,containsString("prefixed"));
}
