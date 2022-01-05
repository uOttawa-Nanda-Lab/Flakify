@Test public void testSmoke(){
  FileNamePattern pp=new FileNamePattern("t",context);
  assertEquals("t",pp.convertInt(3));
  pp=new FileNamePattern("foo",context);
  assertEquals("foo",pp.convertInt(3));
  pp=new FileNamePattern("%i foo",context);
  assertEquals("3 foo",pp.convertInt(3));
  pp=new FileNamePattern("foo%i.xixo",context);
  assertEquals("foo3.xixo",pp.convertInt(3));
  pp=new FileNamePattern("foo%i.log",context);
  assertEquals("foo3.log",pp.convertInt(3));
  pp=new FileNamePattern("foo.%i.log",context);
  assertEquals("foo.3.log",pp.convertInt(3));
}
