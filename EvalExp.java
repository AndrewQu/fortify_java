/**
 * The main EvalExp class
 */
public class EvalExp {
	enum OperatorType {
		plus,
		minus,
		time,
		divide,
		bracket,	// Left bracket
		operand;	// Numbers
		public int Priority() {	return ordinal() / 2; }
	};
	static class InvalidTokenException extends Exception {
		public InvalidTokenException(String msg) {
			super(msg);
		}
	}

    interface DoubleMath {
        double operation(double a, double b);
    }

	static DoubleMath[] ops = new DoubleMath[] {
		new DoubleMath() { public double operation(double a, double b) { return a + b; } },
		new DoubleMath() { public double operation(double a, double b) { return a - b; } },
		new DoubleMath() { public double operation(double a, double b) { return a * b; } },
		new DoubleMath() { public double operation(double a, double b) { return a / b; } }
	};

	static double Evaluate(String exp) throws InvalidTokenException {
		// Local class
		class Token {
			OperatorType opt;
			double operandValue;
			public Token(OperatorType opt, double value) {
				this.opt = opt;
				operandValue = value;
			}
			public boolean IsOperator() {
				return opt.ordinal() <= 4;
			}
			public double GetOperand() { return operandValue; }
			public double Operation(double a, double b) {
				return ops[opt.ordinal()].operation(a, b);
			}
		}
		class TreeNode {
			Token theToken;
			TreeNode parent;
			TreeNode left;
			TreeNode right;
			public TreeNode(Token tkn) {
				theToken = tkn;
				parent = left = right = null;
			}
			public boolean IsOperator() { return theToken.IsOperator(); }
			public int Priority() { return theToken.opt.Priority(); }

			public double EvaluateNode() {
				return this.IsOperator() == false ? theToken.GetOperand() :
					theToken.Operation(left.EvaluateNode(), right.EvaluateNode());
			}

		}
		TreeNode root = null;
		TreeNode lastNode = null;
		for(int i=0; i<exp.length(); i++) {
			Token tkni = null;
			int c = exp.charAt(i);
			switch(c) {
				case '+' : tkni = new Token(OperatorType.plus, 0);		break;
				case '-' : tkni = new Token(OperatorType.minus, 0);		break;
				case '*' : tkni = new Token(OperatorType.time, 0);		break;
				case '/' : tkni = new Token(OperatorType.divide, 0);	break;
				case '(' : tkni = new Token(OperatorType.bracket, 0);	break;
				default :
				    tkni = new Token(OperatorType.operand, Integer.parseInt(exp.substring(i, i+1)));
					break;
			}
			TreeNode node = new TreeNode(tkni);
			if(root == null) {
				lastNode = root = node;
			} else if(lastNode.IsOperator()) {
				lastNode.right = node;
				node.parent = lastNode;
				lastNode = node;
			} else { // Last node is a number
			    if(node.IsOperator() == false)
					throw new InvalidTokenException("Two consecutive numbers in expression.");
				// This node is an operator. Find a node to replace
				TreeNode replaceNode = lastNode;
				// TODO - add code for operator priority check
				node.left = replaceNode;
				node.parent = replaceNode.parent;
				replaceNode.parent = node;
				lastNode = node;
				if(node.parent == null) root = node;
			}
		}
		return root.EvaluateNode();
	}

	public static void main( String args[] ) {
		try {
			System.out.println("Evaluate(\"2 + 3\") = " + Evaluate("2+3"));
			System.out.println("Evaluate(\"2 * 3\") = " + Evaluate("2*3"));
		} catch (InvalidTokenException e) {
			System.out.println("Exception : " + e);
		}
	}
}
