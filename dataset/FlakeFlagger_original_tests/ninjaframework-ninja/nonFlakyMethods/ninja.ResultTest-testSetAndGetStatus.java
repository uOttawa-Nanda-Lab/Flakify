@Test public void testSetAndGetStatus(){
  Result result=new Result(Result.SC_200_OK);
  result.status(Result.SC_501_NOT_IMPLEMENTED);
  assertEquals(Result.SC_501_NOT_IMPLEMENTED,result.getStatusCode());
}
