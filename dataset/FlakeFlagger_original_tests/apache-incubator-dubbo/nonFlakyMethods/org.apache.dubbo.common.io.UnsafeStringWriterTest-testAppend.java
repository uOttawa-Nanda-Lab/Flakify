@Test public void testAppend(){
  UnsafeStringWriter writer=new UnsafeStringWriter();
  writer.append("a");
  writer.append("abc",1,2);
  writer.append('c');
  writer.flush();
  writer.close();
  assertThat(writer.toString(),is("abc"));
}
