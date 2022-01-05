@Test public void testFalseHtmlPage() throws Exception {
  log.trace("False HTML");
  try (CloseableHttpClient httpClient=HttpClients.createDefault()){
    HttpResponse response=httpClient.execute(new HttpGet(webappUrl.toURI() + "test.html"));
    try (InputStream in=response.getEntity().getContent()){
      String content=getContent(in);
      assertThat(content,containsString("1 + 1 = 2"));
    }
   }
 }
