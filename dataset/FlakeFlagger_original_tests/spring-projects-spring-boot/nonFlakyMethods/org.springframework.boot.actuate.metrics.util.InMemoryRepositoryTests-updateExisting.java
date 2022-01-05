@Test public void updateExisting(){
  this.repository.set("foo","spam");
  this.repository.update("foo",new Callback<String>(){
    @Override public String modify(    String current){
      return "bar";
    }
  }
);
  assertEquals("bar",this.repository.findOne("foo"));
}
