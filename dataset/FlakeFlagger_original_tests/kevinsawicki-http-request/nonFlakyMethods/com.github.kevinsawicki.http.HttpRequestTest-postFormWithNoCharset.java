/** 
 * Make a post of form data
 * @throws Exception
 */
@Test public void postFormWithNoCharset() throws Exception {
  final AtomicReference<String> body=new AtomicReference<String>();
  final AtomicReference<String> contentType=new AtomicReference<String>();
  handler=new RequestHandler(){
    @Override public void handle(    Request request,    HttpServletResponse response){
      body.set(new String(read()));
      contentType.set(request.getContentType());
      response.setStatus(HTTP_OK);
    }
  }
;
  Map<String,String> data=new LinkedHashMap<String,String>();
  data.put("name","user");
  data.put("number","100");
  int code=post(url).form(data,null).form("zip","12345").code();
  assertEquals(HTTP_OK,code);
  assertEquals("name=user&number=100&zip=12345",body.get());
  assertEquals("application/x-www-form-urlencoded",contentType.get());
}
