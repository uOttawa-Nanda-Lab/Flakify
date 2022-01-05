@Test(expected=IndexOutOfBoundsException.class) public void testWrongLength() throws IOException {
  UnsafeStringReader reader=new UnsafeStringReader("abc");
  char[] chars=new char[1];
  reader.read(chars,0,2);
}
