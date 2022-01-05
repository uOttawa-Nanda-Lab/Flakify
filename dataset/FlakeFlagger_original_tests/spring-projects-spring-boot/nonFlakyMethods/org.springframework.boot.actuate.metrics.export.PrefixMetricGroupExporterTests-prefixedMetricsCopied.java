@Test public void prefixedMetricsCopied(){
  this.reader.set(new Metric<Number>("foo.bar",2.3));
  this.reader.set(new Metric<Number>("foo.spam",1.3));
  this.exporter.setGroups(Collections.singleton("foo"));
  this.exporter.export();
  assertEquals(1,Iterables.collection(this.writer.groups()).size());
}
