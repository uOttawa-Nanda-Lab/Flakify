@Test public void set(){
  this.repository.set(new Metric<Double>("foo",2.5,new Date()));
  assertEquals(2.5,this.repository.findOne("foo").getValue().doubleValue(),0.01);
}
