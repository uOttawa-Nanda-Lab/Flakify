@Test public void propertySet() throws IOException {
  Map<String,Object> root=new LinkedHashMap<String,Object>();
  root.put("string","abc");
  root.put("int",678);
  root.put("double",3.14d);
  root.put("bool",true);
  assertEquals(root.entrySet(),JsonNodeValueResolver.INSTANCE.propertySet(node(root)));
}
