@Test public void testObject2Json() throws Exception {
  Bean bean=new Bean();
  bean.array=new int[]{1,3,4};
  bean.setName("ql");
  String json=JSON.json(bean);
  bean=JSON.parse(json,Bean.class);
  assertEquals(bean.getName(),"ql");
  assertEquals(bean.getDisplayName(),"钱磊");
  assertEquals(bean.bytes.length,DEFAULT_BYTES.length);
  assertEquals(bean.$$,DEFAULT_$$);
  assertEquals("{\"name\":\"ql\",\"array\":[1,3,4]}",JSON.json(bean,new String[]{"name","array"}));
}
