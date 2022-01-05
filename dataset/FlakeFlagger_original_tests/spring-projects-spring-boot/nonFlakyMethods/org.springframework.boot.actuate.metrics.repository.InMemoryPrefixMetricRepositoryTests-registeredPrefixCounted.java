@Test public void registeredPrefixCounted(){
  this.repository.increment(new Delta<Number>("foo.bar",1));
  this.repository.increment(new Delta<Number>("foo.bar",1));
  this.repository.increment(new Delta<Number>("foo.spam",1));
  Set<String> names=new HashSet<String>();
  for (  Metric<?> metric : this.repository.findAll("foo")) {
    names.add(metric.getName());
  }
  assertEquals(2,names.size());
  assertTrue(names.contains("foo.bar"));
}
