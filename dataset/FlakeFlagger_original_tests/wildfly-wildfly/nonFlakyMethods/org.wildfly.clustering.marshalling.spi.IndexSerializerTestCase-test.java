@Test public void test() throws IOException {
  for (int i=0; i < Integer.SIZE - 2; ++i) {
    int index=2 << i;
    test(index - 1);
    test(index);
  }
  test(Integer.MAX_VALUE);
}
