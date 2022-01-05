@Test public void countOne(){
  this.data.set(new Metric<Integer>("foo",1));
  this.data.set(new Metric<Integer>("foo",1));
  this.exporter.export();
  assertEquals(6,this.repository.count());
  assertEquals(1,this.reader.count());
  RichGauge one=this.reader.findOne("foo");
  assertNotNull(one);
  assertEquals(2,one.getCount());
}
