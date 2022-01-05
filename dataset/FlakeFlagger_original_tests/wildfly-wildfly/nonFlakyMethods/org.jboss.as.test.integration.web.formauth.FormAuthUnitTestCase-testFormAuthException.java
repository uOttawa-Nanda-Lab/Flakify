/** 
 * Test that a bad login is redirected to the errors.jsp and that the session j_exception is not null.
 */
@Test @OperateOnDeployment("form-auth.war") public void testFormAuthException() throws Exception {
  log.trace("+++ testFormAuthException");
  URL url=new URL(baseURLNoAuth + "restricted/SecuredServlet");
  HttpGet httpget=new HttpGet(url.toURI());
  log.trace("Executing request " + httpget.getRequestLine());
  HttpResponse response=httpclient.execute(httpget);
  int statusCode=response.getStatusLine().getStatusCode();
  Header[] errorHeaders=response.getHeaders("X-NoJException");
  assertTrue("Wrong response code: " + statusCode,statusCode == HttpURLConnection.HTTP_OK);
  assertTrue("X-NoJException(" + Arrays.toString(errorHeaders) + ") is null",errorHeaders.length == 0);
  HttpEntity entity=response.getEntity();
  if ((entity != null) && (entity.getContentLength() > 0)) {
    String body=EntityUtils.toString(entity);
    assertTrue("Redirected to login page",body.indexOf("j_security_check") > 0);
  }
 else {
    fail("Empty body in response");
  }
  String sessionID=null;
  for (  Cookie k : httpclient.getCookieStore().getCookies()) {
    if (k.getName().equalsIgnoreCase("JSESSIONID"))     sessionID=k.getValue();
  }
  log.trace("Saw JSESSIONID=" + sessionID);
  HttpPost formPost=new HttpPost(baseURLNoAuth + "j_security_check");
  formPost.addHeader("Referer",baseURLNoAuth + "restricted/login.html");
  List<NameValuePair> formparams=new ArrayList<NameValuePair>();
  formparams.add(new BasicNameValuePair("j_username","baduser"));
  formparams.add(new BasicNameValuePair("j_password","badpass"));
  formPost.setEntity(new UrlEncodedFormEntity(formparams,"UTF-8"));
  log.trace("Executing request " + formPost.getRequestLine());
  HttpResponse postResponse=httpclient.execute(formPost);
  statusCode=postResponse.getStatusLine().getStatusCode();
  errorHeaders=postResponse.getHeaders("X-NoJException");
  assertTrue("Should see HTTP_OK. Got " + statusCode,statusCode == HttpURLConnection.HTTP_OK);
  assertTrue("X-NoJException(" + Arrays.toString(errorHeaders) + ") is not null",errorHeaders.length != 0);
  log.debug("Saw X-JException, " + Arrays.toString(errorHeaders));
}
