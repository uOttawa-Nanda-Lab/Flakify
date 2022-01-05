@Test public void testServlet() throws Exception {
  HttpRequest.get(url.toExternalForm() + "simple",10,SECONDS);
}
