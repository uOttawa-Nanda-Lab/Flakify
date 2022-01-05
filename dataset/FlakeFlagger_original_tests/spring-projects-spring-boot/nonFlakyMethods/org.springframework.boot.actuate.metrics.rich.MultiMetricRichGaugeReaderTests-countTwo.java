@Test public void countTwo(){
  this.data.set(new Metric<Integer>("foo",1));
  this.data.set(new Metric<Integer>("bar",1));
  this.exporter.export();
  assertEquals(2,this.reader.count());
  RichGauge one=this.reader.findOne("foo");
  assertNotNull(one);
  assertEquals(1,one.getCount());
}
