@Test public void multiMetricGroupsCopiedAsDefault(){
  this.reader.set("foo",Arrays.<Metric<?>>asList(new Metric<Number>("bar",2.3),new Metric<Number>("spam",1.3)));
  this.exporter.export();
  assertEquals(1,this.writer.countGroups());
  assertEquals(2,Iterables.collection(this.writer.findAll("foo")).size());
}
