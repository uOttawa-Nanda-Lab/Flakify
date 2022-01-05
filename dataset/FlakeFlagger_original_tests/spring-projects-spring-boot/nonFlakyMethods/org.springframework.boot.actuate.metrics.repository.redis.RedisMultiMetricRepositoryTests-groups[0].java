@Test public void groups(){
  this.repository.set("foo",Arrays.<Metric<?>>asList(new Metric<Number>("foo.val",12.3),new Metric<Number>("foo.bar",11.3)));
  this.repository.set("bar",Arrays.<Metric<?>>asList(new Metric<Number>("bar.val",12.3),new Metric<Number>("bar.foo",11.3)));
  Collection<String> groups=Iterables.collection(this.repository.groups());
  assertEquals(2,groups.size());
  assertTrue("Wrong groups: " + groups,groups.contains("foo"));
}
