@Test public void testResultsTODO(){
  Result result=Results.TODO();
  assertEquals(Result.SC_501_NOT_IMPLEMENTED,result.getStatusCode());
  assertEquals(Result.APPLICATON_JSON,result.getContentType());
}
