package complex_calculator;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

class RPNCalculator extends Calculator {
	Stack<Double> stack = new Stack<Double>();

	@Override
	public String getDisplay() {
		StringWriter output = new StringWriter();
		PrintWriter writer = new PrintWriter(output);
		writer.println("Stack:");
		
		for(double operand : stack)
		{
			writer.println(operand);
		}
		
		return output.toString();
	}

	@Override
	protected void pushOperands(List<Double> operands) {
		for(double operand : operands)
		{
			stack.push(operand);
		}
	}

	@Override
	protected void pushOperator(String operator) {
		setOperator(operator);
	}

	@Override
	protected List<Double> yieldOperands(int amount) {
		List<Double> operands = new ArrayList<Double>();
		for(int i = 0; i < amount; i++)
		{
			operands.add(stack.pop());
		}
		return operands;
	}

	@Override
	protected boolean hasOperands(int amount) {
		return stack.size() >= amount;
	}

}
