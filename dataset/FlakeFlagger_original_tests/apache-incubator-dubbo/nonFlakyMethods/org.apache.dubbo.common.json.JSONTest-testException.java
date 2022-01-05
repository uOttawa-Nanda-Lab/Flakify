@Test public void testException() throws Exception {
  MyException e=new MyException("001","AAAAAAAA");
  StringWriter writer=new StringWriter();
  JSON.json(e,writer);
  String json=writer.getBuffer().toString();
  System.out.println(json);
  StringReader reader=new StringReader(json);
  MyException result=JSON.parse(reader,MyException.class);
  Assert.assertEquals("001",result.getCode());
  Assert.assertEquals("AAAAAAAA",result.getMessage());
}
