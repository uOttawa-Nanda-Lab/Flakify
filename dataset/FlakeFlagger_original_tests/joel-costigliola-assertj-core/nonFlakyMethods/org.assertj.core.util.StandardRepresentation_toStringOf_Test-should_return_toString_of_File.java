@Test public void should_return_toString_of_File(){
  final String path="/someFile.txt";
  @SuppressWarnings("serial") File o=new File(path){
    @Override public String getAbsolutePath(){
      return path;
    }
  }
;
  assertEquals(path,new StandardRepresentation().toStringOf(o));
}
