@Test public void setAndGetMultiple(){
  this.repository.set("foo",Arrays.<Metric<?>>asList(new Metric<Number>("foo.val",12.3),new Metric<Number>("foo.bar",11.3)));
  assertEquals(2,Iterables.collection(this.repository.findAll("foo")).size());
}
