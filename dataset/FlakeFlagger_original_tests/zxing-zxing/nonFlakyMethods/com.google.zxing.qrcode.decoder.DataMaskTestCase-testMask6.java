@Test public void testMask6(){
  testMaskAcrossDimensions(6,new MaskCondition(){
    @Override public boolean isMasked(    int i,    int j){
      return ((i * j) % 2 + (i * j) % 3) % 2 == 0;
    }
  }
);
}
