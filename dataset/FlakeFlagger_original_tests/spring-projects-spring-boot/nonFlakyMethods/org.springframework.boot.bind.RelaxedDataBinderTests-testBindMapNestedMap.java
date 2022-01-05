@Test public void testBindMapNestedMap() throws Exception {
  Map<String,Object> target=new LinkedHashMap<String,Object>();
  BindingResult result=bind(target,"spam: bar\n" + "vanilla.foo.value: 123","vanilla");
  assertEquals(0,result.getErrorCount());
  @SuppressWarnings("unchecked") Map<String,Object> map=(Map<String,Object>)target.get("foo");
  assertEquals("123",map.get("value"));
}
