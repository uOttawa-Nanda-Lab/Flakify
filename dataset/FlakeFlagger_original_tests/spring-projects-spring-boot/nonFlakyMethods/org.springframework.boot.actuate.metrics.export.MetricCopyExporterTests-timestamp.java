@Test public void timestamp(){
  this.reader.set(new Metric<Number>("foo",2.3));
  this.exporter.setEarliestTimestamp(new Date(System.currentTimeMillis() + 10000));
  this.exporter.export();
  assertEquals(0,this.writer.count());
}
