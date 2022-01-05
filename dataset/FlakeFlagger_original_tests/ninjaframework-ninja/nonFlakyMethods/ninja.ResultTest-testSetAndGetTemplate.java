@Test public void testSetAndGetTemplate(){
  Result result=new Result(Result.SC_200_OK);
  result.template("/my/custom/template.ftl.html");
  assertEquals("/my/custom/template.ftl.html",result.getTemplate());
  assertEquals(Result.SC_200_OK,result.getStatusCode());
}
