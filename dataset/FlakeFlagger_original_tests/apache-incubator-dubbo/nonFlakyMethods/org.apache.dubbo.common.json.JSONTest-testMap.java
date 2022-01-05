@Test @SuppressWarnings("unchecked") public void testMap() throws Exception {
  Map<String,String> map=new HashMap<String,String>();
  map.put("aaa","bbb");
  StringWriter writer=new StringWriter();
  JSON.json(map,writer);
  String json=writer.getBuffer().toString();
  Assert.assertEquals("{\"aaa\":\"bbb\"}",json);
  StringReader reader=new StringReader(json);
  Map<String,String> result=JSON.parse(reader,Map.class);
  Assert.assertEquals("bbb",result.get("aaa"));
}
