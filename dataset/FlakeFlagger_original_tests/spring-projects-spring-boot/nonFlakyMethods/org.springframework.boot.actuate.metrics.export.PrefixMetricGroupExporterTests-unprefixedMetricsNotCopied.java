@Test public void unprefixedMetricsNotCopied(){
  this.reader.set(new Metric<Number>("foo.bar",2.3));
  this.reader.set(new Metric<Number>("foo.spam",1.3));
  this.exporter.setGroups(Collections.singleton("bar"));
  this.exporter.export();
  assertEquals(0,Iterables.collection(this.writer.groups()).size());
}
