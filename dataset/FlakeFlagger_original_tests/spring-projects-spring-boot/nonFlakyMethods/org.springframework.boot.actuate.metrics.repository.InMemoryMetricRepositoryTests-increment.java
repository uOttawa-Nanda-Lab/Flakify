@Test public void increment(){
  this.repository.increment(new Delta<Integer>("foo",1,new Date()));
  assertEquals(1.0,this.repository.findOne("foo").getValue().doubleValue(),0.01);
}
