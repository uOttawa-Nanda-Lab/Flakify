@Test public void testSimpleJSP() throws Exception {
  log.trace("Simple JSP");
  try (CloseableHttpClient httpClient=HttpClients.createDefault()){
    HttpResponse response=httpClient.execute(new HttpGet(webappUrl.toURI() + "index.jsp"));
    try (InputStream in=response.getEntity().getContent()){
      String content=getContent(in);
      assertThat(content,containsString("1 + 1 = 2"));
    }
   }
 }
