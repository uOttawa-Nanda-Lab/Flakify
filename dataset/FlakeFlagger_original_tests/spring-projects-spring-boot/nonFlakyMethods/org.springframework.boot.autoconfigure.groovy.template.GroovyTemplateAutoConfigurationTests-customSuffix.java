@Test public void customSuffix() throws Exception {
  registerAndRefreshContext("spring.groovy.template.suffix:.groovytemplate");
  MockHttpServletResponse response=render("suffixed");
  String result=response.getContentAsString();
  assertThat(result,containsString("suffixed"));
}
