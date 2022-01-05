@Test public void testCookieSetCorrectly() throws Exception {
  log.debug("testCookieSetCorrectly()");
  try (CloseableHttpClient httpclient=HttpClients.createDefault()){
    HttpResponse response=httpclient.execute(new HttpGet(cookieReadURL.toURI() + "CookieReadServlet"));
    if (response.getEntity() != null) {
      response.getEntity().getContent().close();
    }
    log.debug("Sending request with cookie");
    response=httpclient.execute(new HttpPost(cookieReadURL.toURI() + "CookieReadServlet"));
  }
 }
