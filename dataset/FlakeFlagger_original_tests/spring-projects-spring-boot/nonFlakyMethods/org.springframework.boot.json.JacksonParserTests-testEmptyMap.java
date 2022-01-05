@Test public void testEmptyMap(){
  Map<String,Object> map=this.parser.parseMap("{}");
  assertEquals(0,map.size());
}
