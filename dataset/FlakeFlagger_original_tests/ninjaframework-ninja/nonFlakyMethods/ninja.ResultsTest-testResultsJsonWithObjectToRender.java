@Test public void testResultsJsonWithObjectToRender(){
  TestObject testObject=new TestObject();
  Result result=Results.json().render(testObject);
  assertEquals(Result.SC_200_OK,result.getStatusCode());
  assertEquals(Result.APPLICATON_JSON,result.getContentType());
  assertEquals(testObject,result.getRenderable());
}
