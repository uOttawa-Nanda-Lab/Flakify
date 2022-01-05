@Test public void prefixWithWildcard(){
  this.repository.increment(new Delta<Number>("foo.bar",1));
  Set<String> names=new HashSet<String>();
  for (  Metric<?> metric : this.repository.findAll("foo.*")) {
    names.add(metric.getName());
  }
  assertEquals(1,names.size());
  assertTrue(names.contains("foo.bar"));
}
