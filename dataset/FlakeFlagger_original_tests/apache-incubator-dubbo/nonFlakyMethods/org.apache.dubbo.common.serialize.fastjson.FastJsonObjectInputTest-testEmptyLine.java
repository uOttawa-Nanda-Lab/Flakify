@Test(expected=EOFException.class) public void testEmptyLine() throws IOException, ClassNotFoundException {
  fastJsonObjectInput=new FastJsonObjectInput(new StringReader(""));
  fastJsonObjectInput.readObject();
}
