/** 
 * Test that a post from an unsecured form to a secured servlet does not loose its data during the redirect to the form login.
 */
@Test @OperateOnDeployment("form-auth.war") public void testPostDataFormAuth() throws Exception {
  log.trace("+++ testPostDataFormAuth");
  URL url=new URL(baseURLNoAuth + "unsecure_form.html");
  HttpGet httpget=new HttpGet(url.toURI());
  log.trace("Executing request " + httpget.getRequestLine());
  HttpResponse response=httpclient.execute(httpget);
  int statusCode=response.getStatusLine().getStatusCode();
  Header[] errorHeaders=response.getHeaders("X-NoJException");
  assertTrue("Wrong response code: " + statusCode,statusCode == HttpURLConnection.HTTP_OK);
  assertTrue("X-NoJException(" + Arrays.toString(errorHeaders) + ") is null",errorHeaders.length == 0);
  EntityUtils.consume(response.getEntity());
  HttpPost restrictedPost=new HttpPost(baseURLNoAuth + "restricted/SecuredPostServlet");
  List<NameValuePair> restrictedParams=new ArrayList<NameValuePair>();
  restrictedParams.add(new BasicNameValuePair("checkParam","123456"));
  restrictedPost.setEntity(new UrlEncodedFormEntity(restrictedParams,"UTF-8"));
  log.trace("Executing request " + restrictedPost.getRequestLine());
  HttpResponse restrictedResponse=httpclient.execute(restrictedPost);
  statusCode=restrictedResponse.getStatusLine().getStatusCode();
  errorHeaders=restrictedResponse.getHeaders("X-NoJException");
  assertTrue("Wrong response code: " + statusCode,statusCode == HttpURLConnection.HTTP_OK);
  assertTrue("X-NoJException(" + Arrays.toString(errorHeaders) + ") is null",errorHeaders.length == 0);
  HttpEntity entity=restrictedResponse.getEntity();
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
  formparams.add(new BasicNameValuePair("j_username","user1"));
  formparams.add(new BasicNameValuePair("j_password","password1"));
  formPost.setEntity(new UrlEncodedFormEntity(formparams,"UTF-8"));
  log.trace("Executing request " + formPost.getRequestLine());
  HttpResponse postResponse=httpclient.execute(formPost);
  statusCode=postResponse.getStatusLine().getStatusCode();
  errorHeaders=postResponse.getHeaders("X-NoJException");
  assertTrue("Should see HTTP_MOVED_TEMP. Got " + statusCode,statusCode == HttpURLConnection.HTTP_MOVED_TEMP);
  assertTrue("X-NoJException(" + Arrays.toString(errorHeaders) + ") is null",errorHeaders.length == 0);
  EntityUtils.consume(postResponse.getEntity());
  Header location=postResponse.getFirstHeader("Location");
  URL indexURI=new URL(location.getValue());
  HttpGet war1Index=new HttpGet(indexURI.toURI());
  log.trace("Executing request " + war1Index.getRequestLine());
  HttpResponse war1Response=httpclient.execute(war1Index);
  statusCode=war1Response.getStatusLine().getStatusCode();
  errorHeaders=war1Response.getHeaders("X-NoJException");
  assertTrue("Wrong response code: " + statusCode,statusCode == HttpURLConnection.HTTP_OK);
  assertTrue("X-NoJException(" + Arrays.toString(errorHeaders) + ") is null",errorHeaders.length == 0);
  HttpEntity war1Entity=war1Response.getEntity();
  if ((war1Entity != null) && (entity.getContentLength() > 0)) {
    String body=EntityUtils.toString(war1Entity);
    if (body.indexOf("j_security_check") > 0)     fail("Get of " + indexURI + " redirected to login page");
  }
 else {
    fail("Empty body in response");
  }
}
