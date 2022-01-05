@Test public void testWriteObject() throws IOException, ClassNotFoundException {
  FullAddress fullAddress=new FullAddress("cId","pN","cityId","Nan Long Street","51000");
  this.fstObjectOutput.writeObject(fullAddress);
  this.flushToInput();
  FullAddress result=this.fstObjectInput.readObject(FullAddress.class);
  assertThat(result,is(fullAddress));
}
