@Test public void testFalseCss() throws Exception {
  log.trace("False CSS");
  try (CloseableHttpClient httpClient=HttpClients.createDefault()){
    HttpResponse response=httpClient.execute(new HttpGet(webappUrl.toURI() + "test.css"));
    try (InputStream in=response.getEntity().getContent()){
      String content=getContent(in);
      assertThat(content,containsString("1 + 1 = 2"));
    }
   }
 }
