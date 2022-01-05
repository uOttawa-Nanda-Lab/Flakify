@Test public void shouldReadProperties(@ArquillianResource URL url) throws Exception {
  HttpRequest.get(url + "index.jsp",10,TimeUnit.SECONDS);
}
