/** 
 * Make a GET and get response body as byte array
 * @throws Exception
 */
@Test public void getBytes() throws Exception {
  handler=new RequestHandler(){
    @Override public void handle(    Request request,    HttpServletResponse response){
      response.setStatus(HTTP_OK);
      write("hello");
    }
  }
;
  HttpRequest request=get(url);
  assertTrue(request.ok());
  assertTrue(Arrays.equals("hello".getBytes(),request.bytes()));
}
