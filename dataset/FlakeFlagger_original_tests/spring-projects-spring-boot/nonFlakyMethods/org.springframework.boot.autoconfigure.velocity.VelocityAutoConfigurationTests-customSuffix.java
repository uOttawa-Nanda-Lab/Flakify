@Test public void customSuffix() throws Exception {
  registerAndRefreshContext("spring.velocity.suffix:.freemarker");
  MockHttpServletResponse response=render("suffixed");
  String result=response.getContentAsString();
  assertThat(result,containsString("suffixed"));
}
