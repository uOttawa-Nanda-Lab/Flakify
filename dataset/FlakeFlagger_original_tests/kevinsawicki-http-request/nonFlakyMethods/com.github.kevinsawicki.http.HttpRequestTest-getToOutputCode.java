/** 
 * Make a GET request and get the code using an  {@link AtomicInteger}
 * @throws Exception
 */
@Test public void getToOutputCode() throws Exception {
  handler=new RequestHandler(){
    @Override public void handle(    Request request,    HttpServletResponse response){
      response.setStatus(HTTP_OK);
    }
  }
;
  AtomicInteger code=new AtomicInteger(0);
  get(url).code(code);
  assertEquals(HTTP_OK,code.get());
}
