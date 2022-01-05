@Test(expected=EOFException.class) public void testEmptySpace() throws IOException, ClassNotFoundException {
  fastJsonObjectInput=new FastJsonObjectInput(new StringReader("  "));
  fastJsonObjectInput.readObject();
}
