@Test @SuppressWarnings("unchecked") public void testLinkedMap() throws Exception {
  LinkedHashMap<String,String> map=new LinkedHashMap<String,String>();
  map.put("aaa","bbb");
  StringWriter writer=new StringWriter();
  JSON.json(map,writer);
  String json=writer.getBuffer().toString();
  Assert.assertEquals("{\"aaa\":\"bbb\"}",json);
  StringReader reader=new StringReader(json);
  LinkedHashMap<String,String> result=JSON.parse(reader,LinkedHashMap.class);
  Assert.assertEquals("bbb",result.get("aaa"));
}
