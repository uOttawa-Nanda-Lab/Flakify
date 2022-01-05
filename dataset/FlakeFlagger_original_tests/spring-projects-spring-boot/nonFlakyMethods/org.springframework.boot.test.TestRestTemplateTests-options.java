@Test public void options() throws Exception {
  TestRestTemplate template=new TestRestTemplate(HtppClientOption.ENABLE_REDIRECTS);
  CustomHttpComponentsClientHttpRequestFactory factory=(CustomHttpComponentsClientHttpRequestFactory)template.getRequestFactory();
  RequestConfig config=factory.getRequestConfig();
  assertThat(config.isRedirectsEnabled(),equalTo(true));
}
