/** 
 * Make a post with an empty form data map
 * @throws Exception
 */
@Test public void postEmptyForm() throws Exception {
  final AtomicReference<String> body=new AtomicReference<String>();
  handler=new RequestHandler(){
    @Override public void handle(    Request request,    HttpServletResponse response){
      body.set(new String(read()));
      response.setStatus(HTTP_OK);
    }
  }
;
  int code=post(url).form(new HashMap<String,String>()).code();
  assertEquals(HTTP_OK,code);
  assertEquals("",body.get());
}
