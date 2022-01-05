/** 
 * Verify sending form data as a sequence of  {@link Entry} objects
 * @throws Exception
 */
@Test public void postFormAsEntries() throws Exception {
  final AtomicReference<String> body=new AtomicReference<String>();
  handler=new RequestHandler(){
    @Override public void handle(    Request request,    HttpServletResponse response){
      body.set(new String(read()));
      response.setStatus(HTTP_OK);
    }
  }
;
  Map<String,String> data=new LinkedHashMap<String,String>();
  data.put("name","user");
  data.put("number","100");
  HttpRequest request=post(url);
  for (  Entry<String,String> entry : data.entrySet())   request.form(entry);
  int code=request.code();
  assertEquals(HTTP_OK,code);
  assertEquals("name=user&number=100",body.get());
}
