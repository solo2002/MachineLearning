package neuralNetwork;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.io.BufferedReader;

public class BuildNetwork {

 static double calculateMSE(double[] expectedOutput, double[] actualOutput)
 {
	 //double result = 0.0;
	 double sum = 0;
	 for(int i = 0; i < expectedOutput.length; i++)
	 {//expectedOutput contains a bias node
		 sum += (expectedOutput[i] - actualOutput[i]) * (expectedOutput[i] - actualOutput[i]);
	 }
	return sum/actualOutput.length;
 }
 static double[] normalizeContinuous(double[] input, double[] mean, double[] sigma)
 {//normalize continuous input sample
	 double[] norm = new double[input.length];
	 for(int i = 0; i < input.length; i++)
	 {
		 norm[i] = (norm[i] - mean[i])/sigma[i];
	 }
	 return norm;
 }
 static void separateData(int i, ArrayList<double[]> inputSample, ArrayList<double[]> expectedOutput,
			ArrayList<double[]> trainingInput, ArrayList<double[]> trainingOutput, ArrayList<double[]> testInput,
			ArrayList<double[]> testOutput) {
		switch (i)
		{
		case 0:
			for(int j = 0; j < inputSample.size()/10; j++)
			{
				testInput.add(inputSample.get(j));
				testOutput.add(expectedOutput.get(j));
			}
			for(int k = inputSample.size()/10; k < inputSample.size(); k++)
			{
				trainingInput.add(inputSample.get(k));
				trainingOutput.add(expectedOutput.get(k));
			}
			break;
		case 1:
			for(int j = 0; j < inputSample.size()/10; j++)
			{
				trainingInput.add(inputSample.get(j));
				trainingOutput.add(expectedOutput.get(j));
			}
			for(int k = inputSample.size()/10; k < inputSample.size() * 2/10; k++)
			{
				testInput.add(inputSample.get(k));
				testOutput.add(expectedOutput.get(k));
			}
			for(int l = inputSample.size() * 2/10; l < inputSample.size(); l++)
			{
				trainingInput.add(inputSample.get(l));
				trainingOutput.add(expectedOutput.get(l));
			}
			break;
		case 2:
			for(int j = 0; j < inputSample.size() * 2/10; j++)
			{
				trainingInput.add(inputSample.get(j));
				trainingOutput.add(expectedOutput.get(j));
			}
			for(int k = inputSample.size() * 2/10; k < inputSample.size() * 3/10; k++)
			{
				testInput.add(inputSample.get(k));
				testOutput.add(expectedOutput.get(k));
			}
			for(int l = inputSample.size() * 3/10; l < inputSample.size(); l++)
			{
				trainingInput.add(inputSample.get(l));
				trainingOutput.add(expectedOutput.get(l));
			}
			break;
		case 3:
			for(int j = 0; j < inputSample.size() * 3/10; j++)
			{
				trainingInput.add(inputSample.get(j));
				trainingOutput.add(expectedOutput.get(j));
			}
			for(int k = inputSample.size() * 3/10; k < inputSample.size() * 4/10; k++)
			{
				testInput.add(inputSample.get(k));
				testOutput.add(expectedOutput.get(k));
			}
			for(int l = inputSample.size() * 4/10; l < inputSample.size(); l++)
			{
				trainingInput.add(inputSample.get(l));
				trainingOutput.add(expectedOutput.get(l));
			}
			break;
		case 4:
			for(int j = 0; j < inputSample.size() * 4/10; j++)
			{
				trainingInput.add(inputSample.get(j));
				trainingOutput.add(expectedOutput.get(j));
			}
			for(int k = inputSample.size() * 4/10; k < inputSample.size() * 5/10; k++)
			{
				testInput.add(inputSample.get(k));
				testOutput.add(expectedOutput.get(k));
			}
			for(int l = inputSample.size() * 5/10; l < inputSample.size(); l++)
			{
				trainingInput.add(inputSample.get(l));
				trainingOutput.add(expectedOutput.get(l));
			}
			break;
		case 5:
			for(int j = 0; j < inputSample.size() * 5/10; j++)
			{
				trainingInput.add(inputSample.get(j));
				trainingOutput.add(expectedOutput.get(j));
			}
			for(int k = inputSample.size() * 5/10; k < inputSample.size() * 6/10; k++)
			{
				testInput.add(inputSample.get(k));
				testOutput.add(expectedOutput.get(k));
			}
			for(int l = inputSample.size() * 6/10; l < inputSample.size(); l++)
			{
				trainingInput.add(inputSample.get(l));
				trainingOutput.add(expectedOutput.get(l));
			}
			break;
		case 6:
			for(int j = 0; j < inputSample.size() * 6/10; j++)
			{
				trainingInput.add(inputSample.get(j));
				trainingOutput.add(expectedOutput.get(j));
			}
			for(int k = inputSample.size() * 6/10; k < inputSample.size() * 7/10; k++)
			{
				testInput.add(inputSample.get(k));
				testOutput.add(expectedOutput.get(k));
			}
			for(int l = inputSample.size() * 7/10; l < inputSample.size(); l++)
			{
				trainingInput.add(inputSample.get(l));
				trainingOutput.add(expectedOutput.get(l));
			}
			break;
		case 7:
			for(int j = 0; j < inputSample.size() * 7/10; j++)
			{
				trainingInput.add(inputSample.get(j));
				trainingOutput.add(expectedOutput.get(j));
			}
			for(int k = inputSample.size() * 7/10; k < inputSample.size() * 8/10; k++)
			{
				testInput.add(inputSample.get(k));
				testOutput.add(expectedOutput.get(k));
			}
			for(int l = inputSample.size() * 8/10; l < inputSample.size(); l++)
			{
				trainingInput.add(inputSample.get(l));
				trainingOutput.add(expectedOutput.get(l));
			}
			break;
		case 8:
			for(int j = 0; j < inputSample.size() * 8/10; j++)
			{
				trainingInput.add(inputSample.get(j));
				trainingOutput.add(expectedOutput.get(j));
			}
			for(int k = inputSample.size() * 8/10; k < inputSample.size() * 9/10; k++)
			{
				testInput.add(inputSample.get(k));
				testOutput.add(expectedOutput.get(k));
			}
			for(int l = inputSample.size() * 9/10; l < inputSample.size(); l++)
			{
				trainingInput.add(inputSample.get(l));
				trainingOutput.add(expectedOutput.get(l));
			}
			break;
		case 9:
			for(int j = 0; j < inputSample.size() * 9/10; j++)
			{
				trainingInput.add(inputSample.get(j));
				trainingOutput.add(expectedOutput.get(j));
			}
			for(int k = inputSample.size() * 9/10; k < inputSample.size(); k++)
			{
				testInput.add(inputSample.get(k));
				testOutput.add(expectedOutput.get(k));
			}
			break;
		default:
			System.out.println("invalid separating training and testing data");
		}
	}	
 static void tenFoldCrossValidation(Network network, ArrayList<double[]> inputSample, 
		 ArrayList<double[]> expectedOutput, int iterationTimes)
	{//10-fold cross-validation
		double[] errRate = new double[10];
		for(int i = 0; i < 10; i++)
		{
			ArrayList<double[]> trainingInput = new ArrayList<>();
			ArrayList<double[]> trainingOutput = new ArrayList<>();
			ArrayList<double[]> testInput = new ArrayList<>();
			ArrayList<double[]> testOutput = new ArrayList<>();
			separateData(i, inputSample, expectedOutput, trainingInput, trainingOutput, testInput,
					testOutput);
			double[] mseVal = new double[iterationTimes];//to save the value of validation MSE during each iteration
			double[] mseTest = new double[iterationTimes];//to save the value of test MSE during each iteration
			int times = 0;
			while(iterationTimes > 0)
			{
				for(int j = 0; j < trainingInput.size() * 8/9; j++)
				{//generate neural network
					network.forward(trainingInput.get(j));
					network.backpropagation(trainingOutput.get(j));
				}
				double mseOfValidation = 0.0;
				for(int l = trainingInput.size() * 8/9; l < trainingInput.size(); l++)
				{
					network.forward(trainingInput.get(l));
					double[] actual = network.layersInNetwork.get(network.numOfNeuron.length - 2).getOutputOfEachNode();
					mseOfValidation += calculateMSE(trainingOutput.get(l), actual);
				}
				mseVal[times] = mseOfValidation/(trainingOutput.size()/9);
				
				//calculate testing data mseTest
				double mseOfTest = 0.0;
				for(int k = 0; k < testInput.size(); k++)
				{
					network.forward(testInput.get(k));
					double[] actual = network.layersInNetwork.get(network.numOfNeuron.length - 2).getOutputOfEachNode();
					mseOfTest += calculateMSE(testOutput.get(k), actual);
				}
				mseTest[times] = mseOfTest/testInput.size();//testInput.size();
				times++;
				iterationTimes--;
			}
			//for(double d : mseVal)
				//System.out.println(d);
		//for(double d : mseTest)
			//System.out.println(d);
			int numOfErr = 0;
			double mseOfTest = 0.0;
			for(int k = 0; k < testInput.size(); k++)
			{
				network.forward(testInput.get(k));
				double[] actual = network.layersInNetwork.get(network.numOfNeuron.length - 2).getOutputOfEachNode();
				mseOfTest += calculateMSE(testOutput.get(k), actual);
				if(maxRank(actual) != maxRank(testOutput.get(k)))
				{
					//System.out.println("actual " + maxRank(actual) + " vs " + "output " + maxRank(testOutput.get(k)));
					numOfErr++;
				}
			}
			//System.out.println(numOfErr);
			errRate[i] = numOfErr * 1.0 / testInput.size();
			System.out.print("errRate-" + i + ": %");
			System.out.printf("%8.4f", errRate[i]*100);
			System.out.println();
		}
		double sum = 0.0;
		for(double d : errRate)
			sum += d;
		double mean = sum/10;
		double squre = 0.0;
		for(int i = 0; i < 10; i++)
		{
			squre += (errRate[i] - mean) * (errRate[i] - mean);
		}
		double sigma = Math.sqrt(squre/10);
		double se = sigma/(Math.sqrt(10)) * 100;
		System.out.print("10 Fold Cross Validation Error Rate: %");
		System.out.printf("%8.4f", mean*100);
		System.out.print("  \u00b1");
		System.out.printf("%8.4f", se);
		System.out.println("\t");
	}
 static int maxRank(double[] array)
 {
	 if(array == null || array.length == 0) return -1;
	 int max = 0;
	 double maxVal = array[max];
	 for(int i = 0; i < array.length; i++)
	 {
		 if(array[i] > maxVal)
		 {
			 maxVal = array[i];
			 max = i;
		 }
	 }
	 return max;
 }
 public static void main(String[] args) throws IOException
	{
		String filename = args[0];
    FileReader fr = new FileReader(filename);
    BufferedReader br = new BufferedReader(fr);
    Scanner scanner = new Scanner(br);
    String[] name = scanner.nextLine().split(",");
    ArrayList<String> attriNames = new ArrayList<String>(Arrays.asList(name));//all attributes name
    ArrayList<ArrayList<String>> possibleAttriOp = new ArrayList<ArrayList<String>>();
    //all possible value for each attribute
    while(scanner.hasNextLine())
    {
    	String[] attriVal = scanner.nextLine().split(",");
    	possibleAttriOp.add(new ArrayList<String>(Arrays.asList(attriVal)));
    }
    //System.out.println(possibleAttriOp);
    
		//read all tuples of data
		String datafile = args[1];
		FileReader fr2 = new FileReader(datafile);
    BufferedReader br2 = new BufferedReader(fr2);
    Scanner scanner2 = new Scanner(br2);
    ArrayList<ArrayList<String>> allTuples = new ArrayList<>();
    while(scanner2.hasNextLine())
    {
    	String[] data = scanner2.nextLine().split(",");
    	allTuples.add(new ArrayList<String>(Arrays.asList(data)));
    }
    Collections.shuffle(allTuples);//shuffle data
		scanner.close();//all data are read
		
		//System.out.println(allTuples);
		ArrayList<double[]> inputSample = new ArrayList<>();
		ArrayList<double[]> expectedOutput= new ArrayList<>();
		for(int i = 0; i < allTuples.size(); i++)
		{
			ArrayList<String> list = allTuples.get(i);
			double[] sample = new double[possibleAttriOp.size() - 1];
			double[] result = new double[possibleAttriOp.get(possibleAttriOp.size() - 1).size()];//the size of class value 
			//or target options
			for(int j = 0; j < list.size() - 1; j++)
			{
				String attribute = list.get(j);
				double d = 0;
				for(int k = 0; k < possibleAttriOp.get(j).size(); k++)
				{
					//System.out.println("Target Value: " + attribute + " current value: " + possibleAttriOp.get(j).get(k) + "\t");
					String val = possibleAttriOp.get(j).get(k);
					if (attribute.equals(val))
					{
						d = k / (double)(1.0 * possibleAttriOp.get(j).size())  - 0.3;//assigned value based on the rank of current attribute value
						break;
					}
				}
				sample[j] = d;
			}
			
			inputSample.add(sample);
			
			String s = list.get(list.size() - 1);
			for(int l = 0; l < possibleAttriOp.get(possibleAttriOp.size() - 1).size(); l++)
			{
				if(s.equals(possibleAttriOp.get(possibleAttriOp.size() - 1).get(l)))
				{
					result[l] = 1;//set final result as[1, 0, 0], [0, 1, 0], [0, 0, 1]
				}
			}
			expectedOutput.add(result);
		}
		scanner2.close();
		
		double learningRate = Double.parseDouble(args[2]);
		int iterationTimes = Integer.parseInt(args[3]);
		//double miniError = Double.parseDouble(args[4]);
		int numOfHiddenLayer = Integer.parseInt(args[4]);
		
		System.out.println("The arguments you entered are as follow");
		System.out.println("Learning Rate: " + learningRate + "\t Iteration Times: " +iterationTimes + "\t Number Of Hidden Layer: " +numOfHiddenLayer);
		int[] numOfNeuron = new int[numOfHiddenLayer + 2];
		numOfNeuron[0] = inputSample.get(0).length + 1;//one feature for one neuron, "1" for bias node
		numOfNeuron[numOfNeuron.length - 1] = expectedOutput.get(0).length + 1;//one neuron for one class, "1" for bias node
		for(int i = 1; i < numOfNeuron.length - 1; i++)
		{
			numOfNeuron[i] = numOfNeuron[0];//(int) Math.sqrt(numOfNeuron[0]) + 1;//use the number of sqrt of first layer' 
			//neurons as hidden layer's neuron, "1" for bias node
		}
		System.out.print("There are " + numOfNeuron.length + " layers in the neural network, and the number of"
				+ " neurons in each layer are: ");
		for(int i : numOfNeuron)
			System.out.print(i + "--");
		//double error = Integer.MAX_VALUE;
		System.out.println();
		Network network = new Network(numOfNeuron, learningRate);
		
		/*while(iterationTimes > 0)
		{
			for(int i = 0; i < inputSample.size()/2; i++)
			{
				network.forward(inputSample.get(i));
				network.backpropagation(expectedOutput.get(i));
			}
			double mse = 0.0;
			for(int i = inputSample.size()/2; i < inputSample.size(); i++)
			{
				network.forward(inputSample.get(i));
				double[] actual = network.layersInNetwork.get(numOfNeuron.length - 2).getOutputOfEachNode();
				mse += calculateMSE(expectedOutput.get(i), actual);
			}
			System.out.println(mse/(inputSample.size()/2));
			iterationTimes--;
		}*/
		
		tenFoldCrossValidation(network, inputSample, expectedOutput, iterationTimes);
		
	}
}
