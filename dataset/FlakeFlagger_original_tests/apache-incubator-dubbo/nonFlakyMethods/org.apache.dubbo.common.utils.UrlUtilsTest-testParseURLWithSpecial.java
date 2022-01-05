@Test public void testParseURLWithSpecial(){
  String address="127.0.0.1:2181?backup=127.0.0.1:2182,127.0.0.1:2183";
  assertEquals("dubbo://" + address,UrlUtils.parseURL(address,null).toString());
}
