package complex_calculator;

import java.util.Iterator;

public abstract class TwoOneOperator extends Operator
{	
	abstract double doOperation(double left, double right);
	
	@Override
	int getArity()
	{
		return 2;
	}

	@Override
	int getCodomainDimentionality()
	{
		return 1;
	}

	@Override
	Iterator<Double> doCalculation(Iterator<Double> args)
	{
		double left = args.next();
		double right = args.next();
		
		double result = doOperation(left, right);
		
		return new DoubleIterable(result);
	}
}
