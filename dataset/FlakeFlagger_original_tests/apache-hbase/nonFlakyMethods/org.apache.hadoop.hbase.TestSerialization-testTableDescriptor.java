public void testTableDescriptor() throws Exception {
  HTableDescriptor htd=createTableDescriptor(getName());
  byte[] mb=Writables.getBytes(htd);
  HTableDescriptor deserializedHtd=(HTableDescriptor)Writables.getWritable(mb,new HTableDescriptor());
  assertEquals(htd.getNameAsString(),deserializedHtd.getNameAsString());
}
