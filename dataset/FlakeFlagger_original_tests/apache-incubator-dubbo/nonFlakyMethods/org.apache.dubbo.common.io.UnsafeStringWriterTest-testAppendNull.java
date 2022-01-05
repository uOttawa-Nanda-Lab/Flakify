@Test public void testAppendNull(){
  UnsafeStringWriter writer=new UnsafeStringWriter();
  writer.append(null);
  writer.append(null,0,4);
  writer.flush();
  writer.close();
  assertThat(writer.toString(),is("nullnull"));
}
