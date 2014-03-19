package complex_calculator;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Stack;

class RPNCalculator extends Calculator {
	Stack<Double> stack = new Stack<Double>();

	@Override
	public String getDisplay() {
		PrintWriter output = new PrintWriter(new StringWriter());
		output.println("Stack:");
		
		for(double operand : stack)
		{
			output.println(operand);
		}
		
		return output.toString();
	}

	@Override
	protected void pushOperands(Iterable<Double> operands) {
		for(double operand : operands)
		{
			stack.push(operand);
		}
	}

	@Override
	protected void pushOperator(Operator operator) {
		// TODO Auto-generated method stub

	}

	@Override
	protected Iterable<Double> yieldOperands(int amount) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected boolean hasOperands(int amount) {
		// TODO Auto-generated method stub
		return false;
	}

}
