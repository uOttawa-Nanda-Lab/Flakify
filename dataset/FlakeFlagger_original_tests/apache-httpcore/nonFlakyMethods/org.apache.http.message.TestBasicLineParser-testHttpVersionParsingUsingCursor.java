@Test public void testHttpVersionParsingUsingCursor() throws Exception {
  String s="HTTP/1.1";
  CharArrayBuffer buffer=new CharArrayBuffer(16);
  buffer.append(s);
  ParserCursor cursor=new ParserCursor(0,s.length());
  LineParser parser=BasicLineParser.DEFAULT;
  HttpVersion version=(HttpVersion)parser.parseProtocolVersion(buffer,cursor);
  Assert.assertEquals("HTTP protocol name","HTTP",version.getProtocol());
  Assert.assertEquals("HTTP major version number",1,version.getMajor());
  Assert.assertEquals("HTTP minor version number",1,version.getMinor());
  Assert.assertEquals("HTTP version number","HTTP/1.1",version.toString());
  Assert.assertEquals(s.length(),cursor.getPos());
  Assert.assertTrue(cursor.atEnd());
  s="HTTP/1.123 123";
  buffer=new CharArrayBuffer(16);
  buffer.append(s);
  cursor=new ParserCursor(0,s.length());
  version=(HttpVersion)parser.parseProtocolVersion(buffer,cursor);
  Assert.assertEquals("HTTP protocol name","HTTP",version.getProtocol());
  Assert.assertEquals("HTTP major version number",1,version.getMajor());
  Assert.assertEquals("HTTP minor version number",123,version.getMinor());
  Assert.assertEquals("HTTP version number","HTTP/1.123",version.toString());
  Assert.assertEquals(' ',buffer.charAt(cursor.getPos()));
  Assert.assertEquals(s.length() - 4,cursor.getPos());
  Assert.assertFalse(cursor.atEnd());
}
