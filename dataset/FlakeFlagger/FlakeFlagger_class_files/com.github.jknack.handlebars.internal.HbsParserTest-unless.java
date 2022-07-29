package com.github.jknack.handlebars.internal;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.tree.ParseTree;
import org.junit.Test;

public class HbsParserTest {

  private boolean printTokens = false;

  @Test public void unless(){parse("{{^block}}{{var}}{{/block}}");}

  private ParseTree parse(final String input) {
    return parse(input, "{{", "}}");
  }

  private ParseTree parse(final String input, final String start, final String delim) {
    final HbsLexer lexer = new HbsLexer(new ANTLRInputStream(input), start, delim);
    CommonTokenStream tokens = new CommonTokenStream(lexer);
    HbsParser parser = new HbsParser(tokens) {
      @Override
      void setStart(final String start) {
        lexer.start = start;
      }

      @Override
      void setEnd(final String end) {
        lexer.end = end;
      }
    };
    ParseTree tree = parser.template();
    if (printTokens) {
      String[] tokenNames = lexer.getTokenNames();
      for (Token token : tokens.getTokens()) {
        int type = token.getType();
        String message = String.format("%s:%s:%s:%s", token.getText(), type == -1 ? ""
            : tokenNames[token.getType()], token.getLine(), token.getCharPositionInLine());
        System.out.println(message);
      }
    }
    System.out.println(tree.toStringTree(parser));
    return tree;
  }
}
