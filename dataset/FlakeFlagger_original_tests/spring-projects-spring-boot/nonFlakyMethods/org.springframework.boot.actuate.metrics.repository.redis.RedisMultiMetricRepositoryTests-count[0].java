@Test public void count(){
  this.repository.set("foo",Arrays.<Metric<?>>asList(new Metric<Number>("foo.val",12.3),new Metric<Number>("foo.bar",11.3)));
  this.repository.set("bar",Arrays.<Metric<?>>asList(new Metric<Number>("bar.val",12.3),new Metric<Number>("bar.foo",11.3)));
  assertEquals(2,this.repository.countGroups());
}
