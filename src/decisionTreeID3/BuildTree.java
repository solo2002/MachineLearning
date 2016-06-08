package decisionTreeID3;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.io.BufferedReader;


public class BuildTree {
	public static void tenFoldCrossValidation(ArrayList<Integer> numOfTuples, ArrayList<Integer> attributes, DataSet ds)
	{//10-fold cross-validation
		double[] errRate = new double[10];
		for(int i = 0; i < 10; i++)
		{
			ArrayList<Integer> trainingData = new ArrayList<>();
			ArrayList<Integer> testData = new ArrayList<>();
			separateTuples(i, numOfTuples, trainingData, testData);
			Node tree = ID3.buildTreeByID3(trainingData, attributes, ds);
			
			int[] counter = ID3.countNode(tree);
			System.out.println("Total number of node: "+ counter[0] + "\t" + "Total number of leaf node: "+ counter[1]);
			tree.printTree(0);
			//tree.outputTree(0);
			errRate[i] = ID3.getErrorRate(tree, testData, ds);
			System.out.println("errRate-" + i + ": %" + errRate[i]*100);
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
		double se = sigma/(Math.sqrt(10)) * 100;
		System.out.print("10 Fold Cross Validation Error Rate: %");
		System.out.printf("%8.4f", mean*100);
		System.out.print("  \u00b1");
		System.out.printf("%8.4f", se);
		System.out.println("\t");
	}
	public static void tenFoldCrossValidationPruning(ArrayList<Integer> numOfTuples, ArrayList<Integer> attributes, DataSet ds)
	{//10-fold cross-validation
		double[] errRate = new double[10];
		for(int i = 0; i < 10; i++)
		{
			ArrayList<Integer> trainingData = new ArrayList<>();
			ArrayList<Integer> testData = new ArrayList<>();
			separateTuples(i, numOfTuples, trainingData, testData);
			Node tree = ID3.buildTreeByID3(trainingData, attributes, ds);
			tree.pessimPrune();
			int[] counter = ID3.countNode(tree);
			System.out.println("After Pruning, total number of node: "+ counter[0] + "\t" + "Total number of leaf node: "+ counter[1]);
			tree.printTree(0);
			//tree.outputTree(0);
			errRate[i] = ID3.getErrorRate(tree, testData, ds);
			System.out.println("After Pruning, errRate-" + i + ": %" + errRate[i]*100);
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
		double se = sigma/(Math.sqrt(10)) * 100;
		System.out.print("After Pruning, 10 Fold Cross Validation Error Rate: %");
		System.out.printf("%8.4f", mean*100);
		System.out.print("  \u00b1");
		System.out.printf("%8.4f", se);
		System.out.println("\t");
	}
	private static void separateTuples(int i, ArrayList<Integer> numOfTuples, ArrayList<Integer> trainingData,
			ArrayList<Integer> testData) {
		switch (i)
		{
		case 0:
			for(int j = 0; j < numOfTuples.size()/10; j++)
				testData.add(numOfTuples.get(j));
			for(int k = numOfTuples.size()/10; k < numOfTuples.size(); k++)
				trainingData.add(numOfTuples.get(k));
			break;
		case 1:
			for(int j = 0; j < numOfTuples.size()/10; j++)
				trainingData.add(numOfTuples.get(j));
			for(int k = numOfTuples.size()/10; k < numOfTuples.size() * 2/10; k++)
				testData.add(numOfTuples.get(k));
			for(int l = numOfTuples.size() * 2/10; l < numOfTuples.size(); l++)
				trainingData.add(numOfTuples.get(l));
			break;
		case 2:
			for(int j = 0; j < numOfTuples.size() * 2/10; j++)
				trainingData.add(numOfTuples.get(j));
			for(int k = numOfTuples.size() * 2/10; k < numOfTuples.size() * 3/10; k++)
				testData.add(numOfTuples.get(k));
			for(int l = numOfTuples.size() * 3/10; l < numOfTuples.size(); l++)
				trainingData.add(numOfTuples.get(l));
			break;
		case 3:
			for(int j = 0; j < numOfTuples.size() * 3/10; j++)
				trainingData.add(numOfTuples.get(j));
			for(int k = numOfTuples.size() * 3/10; k < numOfTuples.size() * 4/10; k++)
				testData.add(numOfTuples.get(k));
			for(int l = numOfTuples.size() * 4/10; l < numOfTuples.size(); l++)
				trainingData.add(numOfTuples.get(l));
			break;
		case 4:
			for(int j = 0; j < numOfTuples.size() * 4/10; j++)
				trainingData.add(numOfTuples.get(j));
			for(int k = numOfTuples.size() * 4/10; k < numOfTuples.size() * 5/10; k++)
				testData.add(numOfTuples.get(k));
			for(int l = numOfTuples.size() * 5/10; l < numOfTuples.size(); l++)
				trainingData.add(numOfTuples.get(l));
			break;
		case 5:
			for(int j = 0; j < numOfTuples.size() * 5/10; j++)
				trainingData.add(numOfTuples.get(j));
			for(int k = numOfTuples.size() * 5/10; k < numOfTuples.size() * 6/10; k++)
				testData.add(numOfTuples.get(k));
			for(int l = numOfTuples.size() * 6/10; l < numOfTuples.size(); l++)
				trainingData.add(numOfTuples.get(l));
			break;
		case 6:
			for(int j = 0; j < numOfTuples.size() * 6/10; j++)
				trainingData.add(numOfTuples.get(j));
			for(int k = numOfTuples.size() * 6/10; k < numOfTuples.size() * 7/10; k++)
				testData.add(numOfTuples.get(k));
			for(int l = numOfTuples.size() * 7/10; l < numOfTuples.size(); l++)
				trainingData.add(numOfTuples.get(l));
			break;
		case 7:
			for(int j = 0; j < numOfTuples.size() * 7/10; j++)
				trainingData.add(numOfTuples.get(j));
			for(int k = numOfTuples.size() * 7/10; k < numOfTuples.size() * 8/10; k++)
				testData.add(numOfTuples.get(k));
			for(int l = numOfTuples.size() * 8/10; l < numOfTuples.size(); l++)
				trainingData.add(numOfTuples.get(l));
			break;
		case 8:
			for(int j = 0; j < numOfTuples.size() * 8/10; j++)
				trainingData.add(numOfTuples.get(j));
			for(int k = numOfTuples.size() * 8/10; k < numOfTuples.size() * 9/10; k++)
				testData.add(numOfTuples.get(k));
			for(int l = numOfTuples.size() * 9/10; l < numOfTuples.size(); l++)
				trainingData.add(numOfTuples.get(l));
			break;
		case 9:
			for(int j = 0; j < numOfTuples.size() * 9/10; j++)
				trainingData.add(numOfTuples.get(j));
			for(int k = numOfTuples.size() * 9/10; k < numOfTuples.size(); k++)
				testData.add(numOfTuples.get(k));
			break;
		default:
			System.out.println("invalid separating training and testing data");
		}
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
		DataSet ds = new DataSet(attriNames, possibleAttriOp);
		Node.dataset = ds;
		ArrayList<Integer> numOfTuples = new ArrayList<>();
		for(int i = 0; i < allTuples.size(); i++)
			numOfTuples.add(i);
		ArrayList<Integer> attributes = new ArrayList<>();
		for(int i = 0; i < attriNames.size() - 1; i++)
			attributes.add(i);
		for(int i = 0; i < allTuples.size(); i++)
		{
			int[] example = new int[attriNames.size()];
			for(int j = 0; j < example.length; j++)
			{
				String attriVal = allTuples.get(i).get(j);
				ArrayList<String> attri = possibleAttriOp.get(j);
				example[j] = attri.indexOf(attriVal);
				//System.out.println("\t" + example[j] +" " + attriVal + "\t" + attri);
			}
			ds.addExample(example);
		}
		scanner2.close();
		Node root = ID3.buildTreeByID3(numOfTuples, attributes, ds);
		//Node root = ID3.buildTreeByID3ByChi(numOfTuples, attributes, ds);
		//int[] counter = ID3.countNode(root);
		//System.out.println("Total number of node: "+ counter[0] + "\t" + "Total number of leaf node: "+ counter[1]);
		//root.printTree(0);
		//ArrayList<Node> leaf = ID3.findLeaf(root);
		tenFoldCrossValidation(numOfTuples, attributes, ds);
		//System.out.println(leaf.size());
		root.pessimPrune();
		tenFoldCrossValidationPruning(numOfTuples, attributes, ds);
		//root.printTree(0);
		//root.outputTree(0);//print to a output file
		
	}
}
