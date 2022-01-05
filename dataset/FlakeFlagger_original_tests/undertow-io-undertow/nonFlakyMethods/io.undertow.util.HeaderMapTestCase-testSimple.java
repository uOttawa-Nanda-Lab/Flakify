@Test public void testSimple(){
  final HeaderMap headerMap=new HeaderMap();
  headerMap.add(Headers.HOST,"yay.undertow.io");
  assertEquals(1,headerMap.size());
  assertNotEquals(-1L,headerMap.fastIterate());
  assertEquals(-1L,headerMap.fiNext(headerMap.fastIterate()));
  assertEquals(Headers.HOST,headerMap.fiCurrent(headerMap.fastIterate()).getHeaderName());
  assertEquals("yay.undertow.io",headerMap.getFirst(Headers.HOST));
  assertEquals("yay.undertow.io",headerMap.getLast(Headers.HOST));
  assertEquals("yay.undertow.io",headerMap.get(Headers.HOST,0));
}
