package complex_calculator;

import java.util.Scanner;

public class CalculatorProgram
{
	Scanner input = new Scanner(System.in);
	Calculator calculator;
	
	public void init()
	{
		String calculatorType = ask("Specify the type of calculator: ");
		
		switch(calculatorType)
		{
			case "simple":
				calculator = new SimpleCalculator();
				break;
			
			default:
				System.out.println("Not implemented");
				init();
		}
	}
	
	
	public void run()
	{
		while(input.hasNextLine())
		{
			doLine(input.nextLine());
		}
	}
	
	
	private void doLine(String line)
	{
		Scanner s = new Scanner(line);
		
		while(s.hasNext())
		{
			if(input.hasNextDouble())
				calculator.takeInputNumber(input.nextDouble());
			else
				calculator.takeInputOperator(input.next());
		}
		
		s.close();
		
		System.out.println(calculator.getDisplay());
	}
	
	
	private String ask(String query)
	{
		System.out.print(query);
		return input.next();
	}

	
	public static void main(String[] args)
	{
		CalculatorProgram program = new CalculatorProgram();
		program.init();
		program.run();
	}
}
