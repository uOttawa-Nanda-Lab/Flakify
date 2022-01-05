@Test public void testSetAndGet() throws Exception {
  Holder<String> holder=new Holder<String>();
  String message="hello";
  holder.set(message);
  assertThat(holder.get(),is(message));
}
