@Test public void testPatternSanitizer() throws Exception {
  String propf="password=123456";
  InputStream is=new ByteArrayInputStream(propf.getBytes());
  PatternSanitizer s=new PatternSanitizer("password=.*","password=*",Filters.TRUE);
  InputStream res=s.sanitize(is);
  byte[] buf=new byte[res.available()];
  res.read(buf);
  assertEquals("password=*",new String(buf));
}
