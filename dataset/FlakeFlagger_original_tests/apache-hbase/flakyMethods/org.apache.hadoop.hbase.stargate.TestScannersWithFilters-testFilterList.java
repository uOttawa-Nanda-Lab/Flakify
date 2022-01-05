public void testFilterList() throws Exception {
  List<Filter> filters=new ArrayList<Filter>();
  filters.add(new RowFilter(CompareOp.EQUAL,new RegexStringComparator(".+-2")));
  filters.add(new QualifierFilter(CompareOp.EQUAL,new RegexStringComparator(".+-2")));
  filters.add(new ValueFilter(CompareOp.EQUAL,new SubstringComparator("One")));
  Filter f=new FilterList(Operator.MUST_PASS_ALL,filters);
  Scan s=new Scan();
  s.addFamily(FAMILIES[0]);
  s.setFilter(f);
  KeyValue[] kvs={new KeyValue(ROWS_ONE[2],FAMILIES[0],QUALIFIERS_ONE[2],VALUES[0])};
  verifyScanFull(s,kvs);
  filters.clear();
  filters.add(new RowFilter(CompareOp.EQUAL,new RegexStringComparator(".+Two.+")));
  filters.add(new QualifierFilter(CompareOp.EQUAL,new RegexStringComparator(".+-2")));
  filters.add(new ValueFilter(CompareOp.EQUAL,new SubstringComparator("One")));
  f=new FilterList(Operator.MUST_PASS_ONE,filters);
  s=new Scan();
  s.setFilter(f);
  verifyScanNoEarlyOut(s,this.numRows,this.colsPerRow);
}
