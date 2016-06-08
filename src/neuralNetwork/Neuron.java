package neuralNetwork;
import java.util.ArrayList;
//contains weights
public class Neuron {

	ArrayList<Double> weight;//an array of weights of next neuron layer
	double output;//output of current neuron, or activation
	double error;//error of output 
	
	public Neuron()
	{//constructor
		output = 0.0;
		error = 0.0;
		weight = new ArrayList<Double>();//an array of Weight
		//initial();
	}
	
	/*private void initial()
	{//initialize weight to -0.5 to 0.5
		for(double d : weight)
			d = 0.5 * Math.random() - 0.5;
	}*/
	public ArrayList<Double> getWeight()
	{
		return weight;
	}
	public double getOutput()
	{
		return output;
	}

	public void initalWeight(int numOfWeight) 
	{//initialize weight to -0.5 to 0.5
		for(int i = 0; i < numOfWeight; i++)
		{
			weight.add(0.5 * Math.random() - 0.5);
		}
		
	}
}
