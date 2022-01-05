/** 
 * If Cache-Control is not set the no-cache strategy has to be applied. We expect Cache-Control: ... Date: ... Expires: ...
 */
@Test public void testAddingOfDefaultHeadersWorks(){
  Result result=Results.json();
  result.render(new Object());
  assertNull(result.getHeaders().get(Result.CACHE_CONTROL));
  assertNull(result.getHeaders().get(Result.DATE));
  assertNull(result.getHeaders().get(Result.EXPIRES));
  resultHandler.handleResult(result,context);
  assertEquals(Result.CACHE_CONTROL_DEFAULT_NOCACHE_VALUE,result.getHeaders().get(Result.CACHE_CONTROL));
  assertNotNull(result.getHeaders().get(Result.DATE));
  assertEquals(DateUtil.formatForHttpHeader(0L),result.getHeaders().get(Result.EXPIRES));
}
