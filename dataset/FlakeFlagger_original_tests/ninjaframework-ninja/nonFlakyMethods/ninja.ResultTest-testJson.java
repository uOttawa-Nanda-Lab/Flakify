@Test public void testJson(){
  Result result=new Result(Result.SC_200_OK);
  result.json();
  assertEquals(Result.APPLICATON_JSON,result.getContentType());
}
