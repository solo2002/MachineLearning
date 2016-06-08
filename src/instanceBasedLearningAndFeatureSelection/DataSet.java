package instanceBasedLearningAndFeatureSelection;
import java.util.ArrayList;

public class DataSet {
	ArrayList<ArrayList<String>> possibleAttriOp;//contain all the values of every attribute, 
	//i.e. wind  include weak and strong, so weak and strong are stored in this list.
	ArrayList<ArrayList<String>> allTuples; //contain tuples of data
	ArrayList<String> attributeNames;//contain all the type of attribute
	ArrayList<String> tuple;//one tuple of data
	
	public DataSet(ArrayList<String> attributeNames, ArrayList<ArrayList<String>> possibleAttriOp)
	{
		this.attributeNames = attributeNames;
		this.possibleAttriOp = possibleAttriOp;
		this.allTuples = new ArrayList<ArrayList<String>>();
	}
	
	public ArrayList<Integer> countDecision(ArrayList<Integer> numOfTuple)
	{//Given an list of number of data, count the number of possible decisions
		int num = possibleAttriOp.size();//number of all attributes types
		int numOfDecision = possibleAttriOp.get(num - 1).size();//the last column contains all the possible decision
		ArrayList<Integer> counter = new ArrayList<Integer>();//contains the number of potential decision
		for(int i = 0; i < numOfDecision; i++)
			counter.add(0);//initialization
		for(int i : numOfTuple)
		{
			String decision = allTuples.get(i).get(possibleAttriOp.size() - 1);//get the decision for tuple i
			int index = possibleAttriOp.get(possibleAttriOp.size() - 1).indexOf(decision);//find the position of this decision.
			int position = counter.get(index).intValue();
			position++;
			counter.set(index, position);//set the number of decisions
		}
		return counter;
	}
	public String mostDecision(ArrayList<Integer> numOfTuple)
	{//calculate the decision with most number, like yes/no in the PlayTennis sample
		//ArrayList<Integer> counter = countDecision(numOfTuple);
		int max = 0;
		for(int i = 0; i < numOfTuple.size(); i++)
		{//for each decision, counter contains its number, and here just find the one (decision) with largest number
			if(numOfTuple.get(i).intValue() > numOfTuple.get(max).intValue())
				max = i;
		}
		String result = possibleAttriOp.get(possibleAttriOp.size() - 1).get(max);//get the string value related decision
		return result;
	}
	
	public String getNameOfAttributeByIndex(int indexOfAttributeOfNode)
	{//get the name of attribute by given an index
		return attributeNames.get(indexOfAttributeOfNode);
	}
	public ArrayList<String> getAttributesByStr(String nodeAttribute)
	{//get an ArrayList of potential value of a given attribute, 
		//i.e., given 'wind', return ["high", "low"]
		int index = attributeNames.indexOf(nodeAttribute);
		return possibleAttriOp.get(index);
	}
	
	public boolean addExample(int[] example){
		if(example.length != attributeNames.size())
			return false;
		ArrayList<String> newExample = new ArrayList<String>();
		for(int i = 0; i < example.length; i++){
			newExample.add(possibleAttriOp.get(i).get(example[i]));
		}
		allTuples.add(newExample);
		return true;
	}
	public static void main(String[] args)
	{
		System.out.println("\u00B1");
	}
}
