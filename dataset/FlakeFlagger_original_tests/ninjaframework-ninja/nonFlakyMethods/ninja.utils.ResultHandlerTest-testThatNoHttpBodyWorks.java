@Test public void testThatNoHttpBodyWorks(){
  Result result=new Result(200);
  result.render(new NoHttpBody());
  resultHandler.handleResult(result,context);
  verify(context).finalizeHeaders(result);
}
