@Test public void testHeaderFormatting() throws Exception {
  Header header1=new BasicHeader("name","value");
  String s=BasicLineFormatter.formatHeader(header1,null);
  Assert.assertEquals("name: value",s);
  Header header2=new BasicHeader("name",null);
  s=BasicLineFormatter.formatHeader(header2,null);
  Assert.assertEquals("name: ",s);
}
