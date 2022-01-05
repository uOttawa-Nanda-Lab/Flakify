@Test public void prefixedMetricsCopied(){
  this.reader.set(new Metric<Number>("foo",2.3));
  this.exporter.export();
  assertEquals(1,Iterables.collection(this.writer.groups()).size());
}
