@Test public void testBindMap() throws Exception {
  Map<String,Object> target=new LinkedHashMap<String,Object>();
  BindingResult result=bind(target,"spam: bar\n" + "vanilla.value: 123","vanilla");
  assertEquals(0,result.getErrorCount());
  assertEquals("123",target.get("value"));
}
