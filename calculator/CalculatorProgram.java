package calculator;

import java.util.Scanner;

class CalculatorProgram
{
	Scanner input = new Scanner(System.in);
	Calculator calc;
	
	public void init()
	{
		System.out.print("Type of calculator: ");
		String type = input.next();
		
		if(type.equals("simple"))
			calc = new SimpleCalculator();
		
		if(type.equals("rpn"))
			calc = new RPNCalculator();
	}
	
	public void run()
	{
		while(input.hasNext())
		{
			if(input.hasNextDouble())
				calc.takeInputNumber(input.nextDouble());
			else
				calc.takeInputOperator(input.next().charAt(0));
			
			if(calc.hasOutput())
				System.out.println(calc.getOutput());
		}
	}
	
	public static void main(String[] args)
	{
		CalculatorProgram program = new CalculatorProgram();
		program.init();
		program.run();
	}
}
