/** 
 * Verify POST with numeric query parameters
 * @throws Exception
 */
@Test public void postWithNumericQueryParams() throws Exception {
  Map<Object,Object> inputParams=new HashMap<Object,Object>();
  inputParams.put(1,2);
  inputParams.put(3,4);
  final Map<String,String> outputParams=new HashMap<String,String>();
  final AtomicReference<String> method=new AtomicReference<String>();
  handler=new RequestHandler(){
    @Override public void handle(    Request request,    HttpServletResponse response){
      method.set(request.getMethod());
      outputParams.put("1",request.getParameter("1"));
      outputParams.put("3",request.getParameter("3"));
      response.setStatus(HTTP_OK);
    }
  }
;
  HttpRequest request=post(url,inputParams,false);
  assertTrue(request.ok());
  assertEquals("POST",method.get());
  assertEquals("2",outputParams.get("1"));
  assertEquals("4",outputParams.get("3"));
}
