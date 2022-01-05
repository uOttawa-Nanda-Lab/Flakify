@Test public void testWrite(){
  UnsafeStringWriter writer=new UnsafeStringWriter();
  writer.write("a");
  writer.write("abc",1,1);
  writer.write(99);
  writer.flush();
  writer.close();
  assertThat(writer.toString(),is("abc"));
}
