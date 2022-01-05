@Test public void testMask4(){
  testMaskAcrossDimensions(4,new MaskCondition(){
    @Override public boolean isMasked(    int i,    int j){
      return (i / 2 + j / 3) % 2 == 0;
    }
  }
);
}
