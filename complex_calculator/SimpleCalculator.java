package complex_calculator;

class SimpleCalculator extends Calculator
{
	Operator operator;
	Double left = 0.0;
	Double right;

	@Override
	protected boolean hasOperands(int amount) {
		if(right != null)
			return amount <= 2;
		else
			return amount <= 1;
	}
	
	@Override
	protected void pushOperands(Iterable<Double> operands) {
		if(!operands.hasNext())
			return;
		
		double operand = operands.next();
		
		if(operands.hasNext())
			throw new IllegalArgumentException(
					"SimplCcalculator can only use operators returning zero or one values.");
		
		if(operator != null)
			right = operand;
		else
			left = operand;
	}

	@Override
	protected void pushOperator(Operator operator) {
		this.operator = operator;
		setOperator(operator);
	}

	@Override
	protected Iterable<Double> yieldOperands(int amount)
	{
		switch (amount) {
		case 0:
			return new DoubleIterable();

		case 1:
			return new DoubleIterable(left);

		case 2:
			return new DoubleIterable(left, right);

		default:
			throw new IllegalArgumentException(
					"SimpleCalculator can only use operators requireing two or less operators.");
		}
	}


	
	@Override
	public String getDisplay() {
		StringBuilder sb = new StringBuilder();
		sb.append(left);
		if(operator != null)
		{
			sb.append(" ");
			sb.append(operator);
		}
		
		return sb.toString();
	}
}
