@Test public void customPrefix() throws Exception {
  registerAndRefreshContext("spring.freemarker.prefix:prefix/");
  MockHttpServletResponse response=render("prefixed");
  String result=response.getContentAsString();
  assertThat(result,containsString("prefixed"));
}
