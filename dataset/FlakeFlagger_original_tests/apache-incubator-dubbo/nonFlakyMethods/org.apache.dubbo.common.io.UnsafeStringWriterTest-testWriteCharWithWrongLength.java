@Test(expected=IndexOutOfBoundsException.class) public void testWriteCharWithWrongLength() throws IOException {
  UnsafeStringWriter writer=new UnsafeStringWriter();
  char[] chars=new char[0];
  writer.write(chars,0,1);
}
