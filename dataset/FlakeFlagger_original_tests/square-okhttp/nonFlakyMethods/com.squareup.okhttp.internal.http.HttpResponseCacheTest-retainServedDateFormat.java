/** 
 * For Last-Modified and Date headers, we should echo the date back in the exact format we were served.
 */
@Test public void retainServedDateFormat() throws Exception {
  Date lastModifiedDate=new Date(System.currentTimeMillis() + TimeUnit.HOURS.toMillis(-1));
  Date servedDate=new Date(System.currentTimeMillis() + TimeUnit.HOURS.toMillis(-2));
  DateFormat dateFormat=new SimpleDateFormat("EEE dd-MMM-yyyy HH:mm:ss z",Locale.US);
  dateFormat.setTimeZone(TimeZone.getTimeZone("EDT"));
  String lastModifiedString=dateFormat.format(lastModifiedDate);
  String servedString=dateFormat.format(servedDate);
  server.enqueue(new MockResponse().addHeader("Last-Modified: " + lastModifiedString).addHeader("Expires: " + servedString).setBody("A"));
  server.enqueue(new MockResponse().setResponseCode(HttpURLConnection.HTTP_NOT_MODIFIED));
  server.play();
  assertEquals("A",readAscii(openConnection(server.getUrl("/"))));
  assertEquals("A",readAscii(openConnection(server.getUrl("/"))));
  RecordedRequest request1=server.takeRequest();
  assertNull(request1.getHeader("If-Modified-Since"));
  RecordedRequest request2=server.takeRequest();
  assertEquals(lastModifiedString,request2.getHeader("If-Modified-Since"));
}
