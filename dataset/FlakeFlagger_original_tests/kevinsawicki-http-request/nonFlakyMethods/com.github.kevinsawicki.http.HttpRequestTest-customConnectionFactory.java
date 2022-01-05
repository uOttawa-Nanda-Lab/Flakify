/** 
 * Verify custom connection factory
 */
@Test public void customConnectionFactory() throws Exception {
  handler=new RequestHandler(){
    @Override public void handle(    Request request,    HttpServletResponse response){
      response.setStatus(HTTP_OK);
    }
  }
;
  ConnectionFactory factory=new ConnectionFactory(){
    public HttpURLConnection create(    URL otherUrl) throws IOException {
      return (HttpURLConnection)new URL(url).openConnection();
    }
    public HttpURLConnection create(    URL url,    Proxy proxy) throws IOException {
      throw new IOException();
    }
  }
;
  HttpRequest.setConnectionFactory(factory);
  int code=get("http://not/a/real/url").code();
  assertEquals(200,code);
}
