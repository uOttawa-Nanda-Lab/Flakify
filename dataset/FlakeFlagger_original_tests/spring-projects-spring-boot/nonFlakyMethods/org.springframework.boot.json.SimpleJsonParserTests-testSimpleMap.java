@Test public void testSimpleMap(){
  Map<String,Object> map=this.parser.parseMap("{\"foo\":\"bar\",\"spam\":1}");
  assertEquals(2,map.size());
  assertEquals("bar",map.get("foo"));
  assertEquals(1L,((Number)map.get("spam")).longValue());
}
