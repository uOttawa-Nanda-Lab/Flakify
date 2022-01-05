@Test public void testDefaultContent() throws Exception {
  String s="Message content";
  StringEntity httpentity=new StringEntity(s,ContentType.create("text/csv","ANSI_X3.4-1968"));
  Assert.assertEquals("text/csv; charset=ansi_x3.4-1968",httpentity.getContentType().getValue());
  httpentity=new StringEntity(s,HTTP.US_ASCII);
  Assert.assertEquals("text/plain; charset=us-ascii",httpentity.getContentType().getValue());
  httpentity=new StringEntity(s);
  Assert.assertEquals("text/plain; charset=ISO-8859-1",httpentity.getContentType().getValue());
}
