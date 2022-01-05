@Test public void writeAndRead(){
  this.repository.set(new Metric<Double>("foo",1d));
  this.repository.set(new Metric<Double>("foo",2d));
  assertEquals(2L,this.repository.findOne("foo").getCount());
  assertEquals(2d,this.repository.findOne("foo").getValue(),0.01);
}
