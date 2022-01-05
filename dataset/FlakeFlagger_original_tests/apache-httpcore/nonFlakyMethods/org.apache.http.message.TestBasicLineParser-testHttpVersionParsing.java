@Test public void testHttpVersionParsing() throws Exception {
  String s="HTTP/1.1";
  HttpVersion version=(HttpVersion)BasicLineParser.parseProtocolVersion(s,null);
  Assert.assertEquals("HTTP protocol name","HTTP",version.getProtocol());
  Assert.assertEquals("HTTP major version number",1,version.getMajor());
  Assert.assertEquals("HTTP minor version number",1,version.getMinor());
  Assert.assertEquals("HTTP version number",s,version.toString());
  s="HTTP/123.4567";
  version=(HttpVersion)BasicLineParser.parseProtocolVersion(s,null);
  Assert.assertEquals("HTTP protocol name","HTTP",version.getProtocol());
  Assert.assertEquals("HTTP major version number",123,version.getMajor());
  Assert.assertEquals("HTTP minor version number",4567,version.getMinor());
  Assert.assertEquals("HTTP version number",s,version.toString());
}
