@Test public void testCacheControlDoesNotGetTouchedWhenSet(){
  Result result=Results.json();
  result.addHeader(Result.CACHE_CONTROL,"must-revalidate");
  result.render(new Object());
  resultHandler.handleResult(result,context);
  assertEquals("must-revalidate",result.getHeaders().get(Result.CACHE_CONTROL));
  assertNull(result.getHeaders().get(Result.DATE));
  assertNull(result.getHeaders().get(Result.EXPIRES));
}
