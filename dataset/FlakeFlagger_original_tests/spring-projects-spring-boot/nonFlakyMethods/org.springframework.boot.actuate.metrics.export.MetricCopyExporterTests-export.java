@Test public void export(){
  this.reader.set(new Metric<Number>("foo",2.3));
  this.exporter.export();
  assertEquals(1,this.writer.count());
}
