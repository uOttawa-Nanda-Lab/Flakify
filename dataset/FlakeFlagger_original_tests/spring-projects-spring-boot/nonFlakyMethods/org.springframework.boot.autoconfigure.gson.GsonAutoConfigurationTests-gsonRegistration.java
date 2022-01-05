@Test public void gsonRegistration(){
  this.context.register(GsonAutoConfiguration.class);
  this.context.refresh();
  Gson gson=this.context.getBean(Gson.class);
  assertEquals("{\"data\":\"hello\"}",gson.toJson(new DataObject()));
}
