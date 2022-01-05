@Test public void should_create_new_Index(){
  Index index=Index.atIndex(8);
  assertEquals(8,index.value);
}
