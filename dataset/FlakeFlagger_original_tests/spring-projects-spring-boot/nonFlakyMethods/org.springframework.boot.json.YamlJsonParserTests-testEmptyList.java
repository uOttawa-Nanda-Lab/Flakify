@Test public void testEmptyList(){
  List<Object> list=this.parser.parseList("[]");
  assertEquals(0,list.size());
}
