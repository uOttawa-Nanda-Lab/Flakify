@Test(expected=IOException.class) public void testWrongClassInput() throws IOException, ClassNotFoundException {
  this.fstObjectInput=new FstObjectInput(new ByteArrayInputStream("{animal: 'cat'}".getBytes()));
  fstObjectInput.readObject(FullAddress.class);
}
