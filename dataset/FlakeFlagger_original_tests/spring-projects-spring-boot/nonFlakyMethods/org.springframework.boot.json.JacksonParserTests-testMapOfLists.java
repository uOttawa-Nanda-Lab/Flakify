@SuppressWarnings("unchecked") @Test public void testMapOfLists(){
  Map<String,Object> map=this.parser.parseMap("{\"foo\":[{\"foo\":\"bar\",\"spam\":1},{\"foo\":\"baz\",\"spam\":2}]}");
  assertEquals(1,map.size());
  assertEquals(2,((List<Object>)map.get("foo")).size());
}
