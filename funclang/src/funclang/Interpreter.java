package funclang;
import java.io.IOException;

import funclang.Env;
import funclang.Value;
import funclang.AST.*;

/**
 * This main class implements the Read-Eval-Print-Loop of the interpreter with
 * the help of Reader, Evaluator, and Printer classes. 
 * 
 * @author hridesh
 *
 */
public class Interpreter {
	public static void main(String[] args) {
		System.out.println(" ____        __     _____ ");
		System.out.println("|  _ \\ _   _ \\ \\   / ____|");
		System.out.println("| |_) | | | | \\ \\ | |     ");
		System.out.println("|  __/| |_| |  > >| |____ ");
		System.out.println("|_|    \\__, | /_/  \\_____|");
		System.out.println("       |___/              ");
		System.out.println("");
		System.out.println("Welcome to PyC! Enter some Python code to evaluate it. Use {} for scoping.");
		System.out.println("Type 'run' <filename>' to run a file. All files are in the src/funclang/examples directory.");
		Reader reader = new Reader();
		Evaluator eval = new Evaluator(reader);
		Printer printer = new Printer();
		REPL: while (true) { // Read-Eval-Print-Loop (also known as REPL)
			Program p = null;
			try {
				p = reader.read();
				if(p._e == null) continue REPL;
				Value val = eval.valueOf(p);
				printer.print(val);
			} catch (Env.LookupException e) {
				printer.print(e);
			} catch (IOException e) {
				System.out.println("Error reading input:" + e.getMessage());
			} catch (NullPointerException e) {
				System.out.println("Error:" + e.getMessage());
			}
		}
	}
}
