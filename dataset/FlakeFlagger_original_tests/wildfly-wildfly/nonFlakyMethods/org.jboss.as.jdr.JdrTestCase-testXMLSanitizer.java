@Test public void testXMLSanitizer() throws Exception {
  String xml="<test><password>foobar</password></test>";
  InputStream is=new ByteArrayInputStream(xml.getBytes());
  XMLSanitizer s=new XMLSanitizer("//password",Filters.TRUE);
  InputStream res=s.sanitize(is);
  byte[] buf=new byte[res.available()];
  res.read(buf);
  assertEquals("<?xml version=\"1.0\" encoding=\"UTF-8\"?><test><password/></test>",new String(buf));
}
