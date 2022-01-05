@Test public void ignoreTimestamp(){
  this.reader.set(new Metric<Number>("foo",2.3));
  this.exporter.setIgnoreTimestamps(true);
  this.exporter.setEarliestTimestamp(new Date(System.currentTimeMillis() + 10000));
  this.exporter.export();
  assertEquals(1,this.writer.count());
}
