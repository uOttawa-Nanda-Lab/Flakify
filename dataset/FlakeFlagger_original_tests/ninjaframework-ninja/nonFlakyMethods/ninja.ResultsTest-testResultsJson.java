@Test public void testResultsJson(){
  Result result=Results.json();
  assertEquals(Result.SC_200_OK,result.getStatusCode());
  assertEquals(Result.APPLICATON_JSON,result.getContentType());
}
