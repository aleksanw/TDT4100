package calculator;

import java.util.Stack;

public class RPNCalculator extends Calculator {
	private Stack<Double> stack = new Stack<Double>();
	private boolean hasResult = false;

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
	}

	@Override
	public boolean hasOutput() {
		return hasResult;
	}

	@Override
	public double getOutput() {
		return getResult();
	}

}
