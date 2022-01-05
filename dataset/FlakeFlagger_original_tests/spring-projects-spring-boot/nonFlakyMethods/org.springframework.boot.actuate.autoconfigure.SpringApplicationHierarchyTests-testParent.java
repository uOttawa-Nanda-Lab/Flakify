@Test public void testParent(){
  SpringApplicationBuilder builder=new SpringApplicationBuilder(Child.class);
  builder.parent(Parent.class);
  this.context=builder.run("--server.port=0");
}
