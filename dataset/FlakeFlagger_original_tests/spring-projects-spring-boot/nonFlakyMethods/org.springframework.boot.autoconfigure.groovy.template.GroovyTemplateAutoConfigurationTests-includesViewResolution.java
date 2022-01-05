@Test public void includesViewResolution() throws Exception {
  registerAndRefreshContext();
  MockHttpServletResponse response=render("includes");
  String result=response.getContentAsString();
  assertThat(result,containsString("here"));
  assertThat(response.getContentType(),equalTo("text/html;charset=UTF-8"));
}
