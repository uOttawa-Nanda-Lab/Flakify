@Test public void testCookieRetrievedCorrectly() throws Exception {
  log.trace("testCookieRetrievedCorrectly()");
  try (CloseableHttpClient httpclient=HttpClients.createDefault()){
    HttpClientContext context=HttpClientContext.create();
    HttpResponse response=httpclient.execute(new HttpGet(cookieURL.toURI() + "CookieServlet"),context);
    int postStatusCode=response.getStatusLine().getStatusCode();
    Header[] postErrorHeaders=response.getHeaders("X-Exception");
    assertTrue("Wrong response code: " + postStatusCode,postStatusCode == HttpURLConnection.HTTP_OK);
    assertTrue("X-Exception(" + Arrays.toString(postErrorHeaders) + ") is null",postErrorHeaders.length == 0);
    List<Cookie> cookies=context.getCookieStore().getCookies();
    assertTrue("Sever did not set expired cookie on client",checkNoExpiredCookie(cookies));
    for (    Cookie cookie : cookies) {
      log.trace("Cookie : " + cookie);
      String cookieName=cookie.getName();
      String cookieValue=cookie.getValue();
      if (cookieName.equals("simpleCookie")) {
        assertTrue("cookie value should be jboss",cookieValue.equals("jboss"));
        assertEquals("cookie path","/jbosstest-cookie",cookie.getPath());
        assertEquals("cookie persistence",false,cookie.isPersistent());
      }
 else       if (cookieName.equals("withSpace")) {
        assertEquals("should be no quote in cookie with space",cookieValue.indexOf("\""),-1);
      }
 else       if (cookieName.equals("comment")) {
        log.trace("comment in cookie: " + cookie.getComment());
        assertTrue(cookie.getComment() == null);
      }
 else       if (cookieName.equals("withComma")) {
        assertTrue("should contain a comma",cookieValue.indexOf(",") != -1);
      }
 else       if (cookieName.equals("expireIn10Sec")) {
        Date now=new Date();
        log.trace("will sleep for 5 seconds to see if cookie expires");
        assertTrue("cookies should not be expired by now",!cookie.isExpired(new Date(now.getTime() + fiveSeconds)));
        log.trace("will sleep for 5 more secs and it should expire");
        assertTrue("cookies should be expired by now",cookie.isExpired(new Date(now.getTime() + 2 * fiveSeconds)));
      }
    }
  }
 }
