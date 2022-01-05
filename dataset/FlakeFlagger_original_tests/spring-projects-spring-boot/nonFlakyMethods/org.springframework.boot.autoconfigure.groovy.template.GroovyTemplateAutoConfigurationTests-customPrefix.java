@Test public void customPrefix() throws Exception {
  registerAndRefreshContext("spring.groovy.template.prefix:classpath:/templates/prefix/");
  MockHttpServletResponse response=render("prefixed");
  String result=response.getContentAsString();
  assertThat(result,containsString("prefixed"));
}
