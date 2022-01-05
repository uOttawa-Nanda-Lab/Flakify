@Test public void localeViewResolution() throws Exception {
  LocaleContextHolder.setLocale(Locale.FRENCH);
  registerAndRefreshContext();
  MockHttpServletResponse response=render("includes",Locale.FRENCH);
  String result=response.getContentAsString();
  assertThat(result,containsString("voila"));
  assertThat(response.getContentType(),equalTo("text/html;charset=UTF-8"));
}
