package calculator;

import java.util.Stack;

public class RPNCalculator extends SimpleCalculator {
	private Stack<Double> stack = new Stack<Double>();

	@Override
	public void takeInputNumber(double token) {
		stack.push(token);
		this.hasResult = false;
	}

	@Override
	public void takeInputOperator(char token) {
		setRightOperand(stack.pop());
		setLeftOperand(stack.pop());
		setOperator(token);
		stack.push(getResult());
		this.hasResult = true;
		this.result = getResult();
	}
}
