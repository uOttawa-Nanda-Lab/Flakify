@Test public void resolveValueNode() throws IOException {
  Handlebars handlebars=new Handlebars();
  Map<String,Object> root=new LinkedHashMap<String,Object>();
  root.put("string","abc");
  root.put("int",678);
  root.put("long",6789L);
  root.put("float",7.13f);
  root.put("double",3.14d);
  root.put("bool",true);
  assertEquals("abc 678 6789 7.13 3.14 true",handlebars.compile("{{string}} {{int}} {{long}} {{float}} {{double}} {{bool}}").apply(context(root)));
}
