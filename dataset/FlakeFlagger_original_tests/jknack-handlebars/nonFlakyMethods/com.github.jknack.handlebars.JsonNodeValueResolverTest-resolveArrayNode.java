@Test public void resolveArrayNode() throws IOException {
  Handlebars handlebars=new Handlebars();
  Object item=new Object(){
    @SuppressWarnings("unused") public String getKey(){
      return "pojo";
    }
  }
;
  Map<String,Object> root=new HashMap<String,Object>();
  root.put("array",new Object[]{item});
  assertEquals("pojo",handlebars.compile("{{array.[0].key}}").apply(context(root)));
  assertEquals("pojo",handlebars.compile("{{#array}}{{key}}{{/array}}").apply(context(root)));
}
