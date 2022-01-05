/** 
 * Test configuration
 * @throws Throwable Thrown if case of an error
 */
@Test public void testWebConfiguration() throws Throwable {
  URL servletURL=new URL("http://" + url.getHost() + ":8080/servlet"+ RaServlet.URL_PATTERN);
  BufferedReader br=new BufferedReader(new InputStreamReader(servletURL.openStream()));
  String message=br.readLine();
  assertEquals(RaServlet.SUCCESS,message);
}
