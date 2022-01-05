@Test public void defaultViewResolution() throws Exception {
  registerAndRefreshContext();
  MockHttpServletResponse response=render("home");
  String result=response.getContentAsString();
  assertThat(result,containsString("home"));
  assertThat(response.getContentType(),equalTo("text/html;charset=UTF-8"));
}
