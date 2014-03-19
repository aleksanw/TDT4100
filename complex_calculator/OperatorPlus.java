package complex_calculator;

class OperatorPlus extends TwoOneOperator
{
	@Override
	String getIdentifier()
	{
		return "+";
	}

	@Override
	double doOperation(double left, double right) 
	{
		return left + right;
	}

}
