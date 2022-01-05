@Test public void testDoubleValie(){
  Map<String,Object> map=this.parser.parseMap("{\"foo\":\"bar\",\"spam\":1.23}");
  assertEquals(2,map.size());
  assertEquals("bar",map.get("foo"));
  assertEquals(1.23d,map.get("spam"));
}
