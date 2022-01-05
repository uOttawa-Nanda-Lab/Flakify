@Test public void updateNonexistent(){
  this.repository.update("foo",new Callback<String>(){
    @Override public String modify(    String current){
      return "bar";
    }
  }
);
  assertEquals("bar",this.repository.findOne("foo"));
}
