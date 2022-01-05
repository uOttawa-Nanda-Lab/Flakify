@Test public void shouldAbleHandleRequestForTomcatBinder() throws Exception {
  int port=NetUtils.getAvailablePort();
  URL url=new URL("http","localhost",port,new String[]{Constants.BIND_PORT_KEY,String.valueOf(port)});
  HttpServer httpServer=new TomcatHttpBinder().bind(url,new HttpHandler(){
    @Override public void handle(    HttpServletRequest request,    HttpServletResponse response) throws IOException {
      response.getWriter().write("Tomcat");
    }
  }
);
  String response=Request.Get(url.toJavaURL().toURI()).execute().returnContent().asString();
  assertThat(response,is("Tomcat"));
  httpServer.close();
}
