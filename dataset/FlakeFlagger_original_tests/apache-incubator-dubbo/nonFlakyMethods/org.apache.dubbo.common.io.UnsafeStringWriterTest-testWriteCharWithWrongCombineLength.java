@Test(expected=IndexOutOfBoundsException.class) public void testWriteCharWithWrongCombineLength() throws IOException {
  UnsafeStringWriter writer=new UnsafeStringWriter();
  char[] chars=new char[1];
  writer.write(chars,1,1);
}
