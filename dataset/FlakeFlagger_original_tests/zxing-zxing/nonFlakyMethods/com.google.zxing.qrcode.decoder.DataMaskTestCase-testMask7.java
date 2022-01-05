@Test public void testMask7(){
  testMaskAcrossDimensions(7,new MaskCondition(){
    @Override public boolean isMasked(    int i,    int j){
      return ((i + j) % 2 + (i * j) % 3) % 2 == 0;
    }
  }
);
}
