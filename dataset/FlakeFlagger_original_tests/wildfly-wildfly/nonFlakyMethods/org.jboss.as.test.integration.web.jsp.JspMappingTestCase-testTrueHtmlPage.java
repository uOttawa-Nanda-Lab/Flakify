@Test public void testTrueHtmlPage() throws Exception {
  log.trace("True HTML");
  try (CloseableHttpClient httpClient=HttpClients.createDefault()){
    HttpResponse response=httpClient.execute(new HttpGet(webappUrl.toURI() + "index.html"));
    try (InputStream in=response.getEntity().getContent()){
      String content=getContent(in);
      assertThat(content,not(containsString("1 + 1 = 2")));
    }
   }
 }
