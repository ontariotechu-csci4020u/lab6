import org.antlr.v4.runtime.*;
import org.antlr.v4.gui.TestRig;
import static java.lang.System.out;

public class Check {
    public static void check (String filename) throws Exception {
        ExprLexer lexer = new ExprLexer((CharStream)null);
        TestRig testrig = new TestRig(new String[]{
            "Expr",
            "expr",
            filename,
            "-tree"});
        testrig.process();
    }
    public static void main(String[] args) throws Exception {
        check(args[0]);
    }
}
