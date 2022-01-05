/** 
 * Verify sending form data where entry value is null
 * @throws Exception
 */
@Test public void postFormEntryWithNullValue() throws Exception {
  final AtomicReference<String> body=new AtomicReference<String>();
  handler=new RequestHandler(){
    @Override public void handle(    Request request,    HttpServletResponse response){
      body.set(new String(read()));
      response.setStatus(HTTP_OK);
    }
  }
;
  Map<String,String> data=new LinkedHashMap<String,String>();
  data.put("name",null);
  HttpRequest request=post(url);
  for (  Entry<String,String> entry : data.entrySet())   request.form(entry);
  int code=request.code();
  assertEquals(HTTP_OK,code);
  assertEquals("name=",body.get());
}
