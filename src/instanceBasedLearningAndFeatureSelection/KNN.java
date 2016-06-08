package instanceBasedLearningAndFeatureSelection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.io.BufferedReader;

public class KNN {

 static double calculateDistance(double[] input, double[] query)
 {//calculate square value of euclid distance
	 double sqrDistance = 0;
	 for(int i = 0; i < input.length; i++)
	 {//exclude the last element, which is the class value
		 sqrDistance += (input[i] - query[i]) * (input[i] - query[i]);
	 }
	 return sqrDistance;
 }
 static TargetDistance findMini(TargetDistance[] topK)
 {//find the minimize Target value
	TargetDistance mini = topK[0];
	for(int i = 0; i < topK.length; i++)
	{
		if(topK[i].getDistance() < mini.getDistance())
			mini = topK[i];
	}
	return mini;
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
 
 static double tenFoldCrossValidation(int k, ArrayList<double[]> inputSample, 
		 ArrayList<double[]> expectedOutput)
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
			
			/*System.out.println("Test size " + testInput.size() + " " + "Output size " + testOutput.size());
			System.out.println("TestInPut:");
			for(double[] d: testInput)
			{
				for(double a : d)
					System.out.print(a + "  ");
				System.out.println();
			}
			System.out.println("*********");
			System.out.println("TestOutPut:");
			for(double[] d: testOutput)
			{
				for(double a : d)
					System.out.print(a + "  ");
				System.out.println();
			}
			System.out.println("*********");
			System.out.println("TrainingInPut:");
			for(double[] d : trainingInput)
			{
				for(double dl : d)
					System.out.print(dl + "  ");
				System.out.println();
			}
			System.out.println("*********");*/
			
			if(k > trainingInput.size())
				throw new IllegalArgumentException("The value of k is greater than the number of training sample.");
			
			int numOfErr = 0;
			for(int j = 0; j < testInput.size(); j++)
			{//for every test sample, calculate its distance between other training samples
				TargetDistance[] allDistance = new TargetDistance[trainingInput.size()];
				for(int l = 0; l < trainingInput.size(); l++)
				{
					allDistance[l] = new TargetDistance(calculateDistance(trainingInput.get(l), testInput.get(j)), l);
				}
				sortDistance(allDistance);
				
				double[] sum = new double[testOutput.get(0).length];
				for(int n = 0; n < k; n++)
				{
					int position = allDistance[n].getPositionInTraining();
					for(int q = 0; q < sum.length; q++)
						sum[q] += trainingOutput.get(position)[q];//the largest element is the target value, if all the same, get the last one
				}
				
				if(maxRank(sum) != maxRank(testOutput.get(j)))
				{
					numOfErr++;//System.out.println("Wrong!");
				}
			}
				
			errRate[i] = numOfErr * 1.0 / testInput.size();
			//System.out.print("errRate-" + i + ": %");
			//System.out.printf("%8.4f", errRate[i]*100);
			//System.out.println();	
		
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
		double se = 2.23 * sigma/(Math.sqrt(10)) * 100;
		System.out.print("10 Fold Cross Validation Error Rate: %");
		System.out.printf("%8.4f", mean*100);
		System.out.print("  \u00b1");
		System.out.printf("%8.4f", se);
		System.out.println("\t");
		return mean;
	}
 
 private static void sortDistance(TargetDistance[] allDistance)  
 {//sort TargetDistance by its distance, quick sort
	sort(allDistance, 0, allDistance.length - 1);
 }
private static void sort(TargetDistance[] allDistance, int lo, int hi) {
	int pivot = (hi + lo)/2;
	int i = lo;
	int j = hi;
	while(i <= j)
	{
		while(allDistance[i].getDistance() < allDistance[pivot].getDistance())
			i++;
		while(allDistance[j].getDistance() > allDistance[pivot].getDistance())
			j--;
		if(i <= j)
		{
			exchange(allDistance, i, j);
			i++;
			j--;
		}
	}
	if(lo < j)
		sort(allDistance, lo, j);
	if(i < hi)
		sort(allDistance,i, hi);
}
private static void exchange(TargetDistance[] allDistance, int i, int j) {
	TargetDistance temp = allDistance[i];
	allDistance[i] = allDistance[j];
	allDistance[j] = temp;
}
private static int maxRank(double[] ds) 
 {//calculate the max elements' rank
	int rank = 0;
	for(int i = 1; i < ds.length; i++)
	{
		if(ds[i] > ds[rank])
			rank = i;
	}
	return rank;
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
		ArrayList<double[]> normalizeData = new ArrayList<>();
		int sampleSize = 0;
		for(int j = 0; j < possibleAttriOp.size() - 1; j++)
		{
			sampleSize += possibleAttriOp.get(j).size();
		}
		for(int i = 0; i < allTuples.size(); i++)
		{
			ArrayList<String> list = allTuples.get(i);
			/*normalize discrete feature, i.e., color feature: red, blue, yellow
			 * convert to [1,0,0], [0,1,0], [0,0,1]
			 */
			double[] result = new double[possibleAttriOp.get(possibleAttriOp.size() - 1).size()];//the size of class value or target options
			for(int j = 0; j < list.size() - 1; j++)
			{
				String attribute = list.get(j);
				double[] sample = new double[possibleAttriOp.get(j).size()];
				for(int k = 0; k < possibleAttriOp.get(j).size(); k++)
				{
					//System.out.println(possibleAttriOp.get(j));
					String val = possibleAttriOp.get(j).get(k);
					if (attribute.equals(val))
					{
						sample[k] = 1.0;
						break;
					}
				}
				normalizeData.add(sample);
			}
			
			String s = list.get(list.size() - 1);
			for(int l = 0; l < possibleAttriOp.get(possibleAttriOp.size() - 1).size(); l++)
			{
				if(s.equals(possibleAttriOp.get(possibleAttriOp.size() - 1).get(l)))
				{
					result[l] = 1 + 0.001 * Math.random();//set final result as[1.001, 0, 0], [0, 1.002, 0], [0, 0, 1.005]
					//the reason of adding 0.01 is to avoid all equal when comparing
				}
			}
			expectedOutput.add(result);
		}
		scanner2.close();
		
		//convert normalizeData to inputSample, {[0,1,0],[1,0],[1,0],[1,0],...} to{[0,1,0,1,0,1,0,1,0],...}
		convert(inputSample, normalizeData, allTuples.get(0).size() - 1, sampleSize);
		
		int k = Integer.parseInt(args[2]);//get k value of k-NN
		System.out.println("The arguments you entered are as follow");
		System.out.println("The value of K: " + k);
		
		double oldErrRate = tenFoldCrossValidation(k, inputSample, expectedOutput);
		System.out.print("Before feature selection, the Error Rate is: %");
		System.out.printf("%8.4f", oldErrRate*100);
		System.out.println();
		//feature selection:
		ArrayList<ArrayList<String>> featureSet = new ArrayList<>();//contains all the features of origninal data except the last class label
		for(int i = 0; i < possibleAttriOp.size() - 1; i++)
			featureSet.add(possibleAttriOp.get(i));
		SBEfeatureSelection(featureSet, oldErrRate, expectedOutput, allTuples, normalizeData, possibleAttriOp, k);
		
	}
private static void SBEfeatureSelection(ArrayList<ArrayList<String>> featureSet, double oldErrRate,
		ArrayList<double[]> expectedOutput, ArrayList<ArrayList<String>> allTuples, ArrayList<double[]> normalizeData,
		ArrayList<ArrayList<String>> possibleAttriOp, 
		int sizeOfK) {
	System.out.println();
	System.out.println("******The feature selection process is beginning******");
	
	while(featureSet.size() >=1)
	{
		int cycleOfBNE = 0;
		ArrayList<Double> errArray = new ArrayList<>();//to save the results of respective error rate after removing each feature 
		for(int q = 0; q < featureSet.size(); q++)
		{
			ArrayList<double[]> removedAllTuples = new ArrayList<>();//save removed data
			
			for(int z = q; z < normalizeData.size(); z = z + featureSet.size() - 1)
			{
				removedAllTuples.add(normalizeData.remove(z));
			}
			//System.out.println("After remove normalizeData size: " + normalizeData.size());
			
			ArrayList<String> currentFeature = featureSet.remove(q);//save current feature 
			//System.out.println("After removed featureSet size: " + featureSet.size());
			ArrayList<double[]> inputSample = new ArrayList<>();
			int sampleSize = 0;
			for(int j = 0; j < featureSet.size(); j++)
				sampleSize += featureSet.get(j).size();
			
			//System.out.println("1st featureSet size: " + featureSet.size() + ", sampleSize size: " + sampleSize );
			convert(inputSample, normalizeData, featureSet.size(), sampleSize);
			errArray.add(tenFoldCrossValidation(sizeOfK, inputSample, expectedOutput));
			featureSet.add(q, currentFeature);//add back current feature
			
			int removedSize = removedAllTuples.size();
			for(int z = 0; z < removedSize; z++)
			{//restore removed feature data
				normalizeData.add(q + z*featureSet.size(), removedAllTuples.remove(0));
			}
		}
		double lowestErr = 1;
		int lowestErrRemoved = -1;
		for(int p = 0; p < errArray.size(); p++)
		{//find the one with lowest err rate
			if(errArray.get(p) < lowestErr)
			{
				lowestErr= errArray.get(p);
				lowestErrRemoved = p;
			}
		}
		if( lowestErr <= oldErrRate)//if(oldErrRate - lowestErr >= 0.001) //if the improvement is less than 0.1%, we could skip it.
		{//if improved, update feature set, allTuplesList and errRate
			cycleOfBNE++;
			System.out.println("In the " + cycleOfBNE + " round of feature selection, the No." + lowestErrRemoved + 
					" feature is removed (starting from No.0)");
			for(int z = lowestErrRemoved; z < normalizeData.size(); z = z + featureSet.size() - 1)
			{
				normalizeData.remove(z);
			}
			featureSet.remove(lowestErrRemoved);
			oldErrRate = lowestErr;
		}
		else
		{
			System.out.println("******The feature selection process is finished******");
			System.out.println();
			System.out.print("After feature selection, the new Error Rate: %");
			System.out.printf("%8.4f", oldErrRate*100);
			System.out.println();
			System.out.println("The number of feature has been removed: " + (possibleAttriOp.size() - 1 - featureSet.size()));
			break;
		}
	}
	
}

private static void convert(ArrayList<double[]> inputSample, ArrayList<double[]> normalizeData, int featureSetSize, int sampleSize) 
{//size: namely feature size, the number of arrays in normalizeData should be converted to input sample
	//sampleSize: new input sample size in a double array; 
	//System.out.println("Normalize data size: " + normalizeData.size()+ " Feature set size: " + featureSetSize + ", sampleSize size: " + sampleSize );
	for(int i = 0; i < normalizeData.size()/featureSetSize; i++)
	{
		double[] sample = new double[sampleSize];
		int counter = 0;
		for(int j = i * featureSetSize; j < featureSetSize * i + featureSetSize; j++)
		{
			double[] data = normalizeData.get(j);
			for(int k = 0; k < data.length; k++)
				sample[counter++] = data[k];
		}
		inputSample.add(sample);
	}
}
}
