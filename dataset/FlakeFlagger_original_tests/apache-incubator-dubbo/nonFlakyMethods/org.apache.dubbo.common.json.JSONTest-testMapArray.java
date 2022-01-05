@Test @SuppressWarnings("unchecked") public void testMapArray() throws Exception {
  Map<String,String> map=new HashMap<String,String>();
  map.put("aaa","bbb");
  StringWriter writer=new StringWriter();
  JSON.json(new Object[]{map},writer);
  String json=writer.getBuffer().toString();
  Assert.assertEquals("[{\"aaa\":\"bbb\"}]",json);
  StringReader reader=new StringReader(json);
  Object[] result=JSON.parse(reader,new Class<?>[]{Map.class});
  Assert.assertEquals(1,result.length);
  Assert.assertEquals("bbb",((Map<String,String>)result[0]).get("aaa"));
}
