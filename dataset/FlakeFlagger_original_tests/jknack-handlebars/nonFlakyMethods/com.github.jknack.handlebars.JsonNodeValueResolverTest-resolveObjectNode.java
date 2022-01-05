@Test public void resolveObjectNode() throws IOException {
  Handlebars handlebars=new Handlebars();
  Object item=new Object(){
    @SuppressWarnings("unused") public String getKey(){
      return "pojo";
    }
  }
;
  Map<String,Object> root=new HashMap<String,Object>();
  root.put("pojo",item);
  assertEquals("pojo",handlebars.compile("{{pojo.key}}").apply(context(root)));
}
