@Test public void testSimpleList(){
  List<Object> list=this.parser.parseList("[\"foo\",\"bar\",1]");
  assertEquals(3,list.size());
  assertEquals("bar",list.get(1));
}
