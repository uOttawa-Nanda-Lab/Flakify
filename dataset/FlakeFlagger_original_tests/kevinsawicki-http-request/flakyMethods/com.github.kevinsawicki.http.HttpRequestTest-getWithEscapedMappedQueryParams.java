/** 
 * Verify GET with escaped query parameters
 * @throws Exception
 */
@Test public void getWithEscapedMappedQueryParams() throws Exception {
  Map<String,String> inputParams=new HashMap<String,String>();
  inputParams.put("name","us er");
  inputParams.put("number","100");
  final Map<String,String> outputParams=new HashMap<String,String>();
  final AtomicReference<String> method=new AtomicReference<String>();
  handler=new RequestHandler(){
    @Override public void handle(    Request request,    HttpServletResponse response){
      method.set(request.getMethod());
      outputParams.put("name",request.getParameter("name"));
      outputParams.put("number",request.getParameter("number"));
      response.setStatus(HTTP_OK);
    }
  }
;
  HttpRequest request=get(url,inputParams,true);
  assertTrue(request.ok());
  assertEquals("GET",method.get());
  assertEquals("us er",outputParams.get("name"));
  assertEquals("100",outputParams.get("number"));
}
