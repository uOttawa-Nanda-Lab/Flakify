@Test public void flowingI(){
{
    FileNamePattern pp=new FileNamePattern("foo%i{}bar%i",context);
    assertEquals("foo3bar3",pp.convertInt(3));
  }
{
    FileNamePattern pp=new FileNamePattern("foo%i{}bar%i",context);
    assertEquals("foo3bar3",pp.convertInt(3));
  }
}
