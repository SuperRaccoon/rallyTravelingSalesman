/*i there! Here's my attempt at your coding challenge that you gave at UC Berkeley's Spring Career Fair about a week ago on Wednesday Jan 28. Sorry it took awhile for me to respond. 

I have a written cover letter which might better explain the skills on my resume and how I acquired them, but I am not sure if I can attach it using this submission form. If you are interested in taking a look at my real cover letter please let me know!


About my code: I did as much as I could in the limited amount of time that I could devote to this. I haven't spent a whole lot of time checking every single possible edge case that could make this program buggy, but I am confident that it should successfully give the best path at least 90% of the time, maybe even all the time! There is one case I know of where if the coordinates are set to be (.0001,.0001) above the effective midpoint of your dots the program might be buggy, but that probably won't happen in real life.

I would love to hear some feedback from you, as this is the first time I have done any sort of coding in a professional environment. Thank you for reading!
*/

import java.lang.reflect.Array;
import java.util.*;


public class RallyThing {

	static ArrayList<Node> myQuick;
	static int total;
	static double maxMiles;
	
	public static void main(String[] args) {
		
		ArrayList<String> myInputs=new ArrayList<String>();
		
		//not completely sure how to have the program take in input through Scanner, so I'm just going to 
		//skip that step and implement it later if that's ok
		
		//first index will have the number of places, very last one will have the miles we can run
		
		
		myInputs.add("31");
		myInputs.add("0 0 office");
		myInputs.add("1 1 mr-smiths");
		myInputs.add("0 1 baked-and-wired");
		myInputs.add("1 0 amc-georgetown");
		myInputs.add("9 0 austin");
		myInputs.add("7 19 boise");
		myInputs.add("11 40 dallas");
		myInputs.add("12 56 atlanta");
		myInputs.add("34 21 delfina");
		myInputs.add("65 34 chilatown");
		myInputs.add("3 4 gold-club");
		myInputs.add("21 11 whole-foods-2");
		myInputs.add("12 23 sushirito");
		myInputs.add("3 6 alexander-steakhouse");
		myInputs.add("9 3 workshop");
		myInputs.add("22 3 district");
		myInputs.add("2 5 cento");
		myInputs.add("1 3 no");
		myInputs.add("4 23 el-paso");
		myInputs.add("4 10 mcdonalds-1");
		myInputs.add("2 8 chicken-and-waffles");
		myInputs.add("4 6 21st-amendment");
		myInputs.add("34 10 huntsville");
		myInputs.add("2 2 detroit");
		myInputs.add("18 36 nyc");
		myInputs.add("24 12 buffalo");
		myInputs.add("30 40 juneau");
		myInputs.add("8 7 ok-city");
		myInputs.add("13 11 cleveland");
		myInputs.add("23 19 chicago");	
		myInputs.add("99 99 honolulu");	
		myInputs.add("100");
		
		//next few lines are some test cases I used. Sorry for them not being too thorough
		
		/*
		myInputs.add("5");
		myInputs.add("0 0 office");
		myInputs.add("1 1 mr-smiths");
		myInputs.add("2 2 baked-and-wired");
		myInputs.add("-1 -1 amc-georgetown");
		myInputs.add("5 5 myhouse");
		myInputs.add("2.5");
		*/
		/*	
		myInputs.add("4");
		myInputs.add("-1 -5 office");
		myInputs.add("1 5 mr-smiths");
		myInputs.add("-1 1 baked-and-wired");
		myInputs.add("-2 2 amc-georgetown");
		myInputs.add("2.5");
		*/
		double myMax=Double.parseDouble(myInputs.get(myInputs.size()-1));
		maxMiles=myMax;
		int total1=Integer.parseInt(myInputs.get(0));
		total=total1;
		//Probably should make an edge case if user inputs 0, though we assume that doesn't happen
		
		int n = 1;
		String firstString=myInputs.get(1);
		ArrayList<Node> myQuickRef=new ArrayList<Node>();
		Node nullNode=new Node();
		myQuickRef.add(nullNode);
		Node startPlace=new Node(firstString);
		myQuickRef.add(startPlace);
		Node currentNode=startPlace;//used for the while loop
		//below while loop won't execute if there's only 1 term, so we don't have to worry about that
		while (n<total){
			Node NextNode=new Node(myInputs.get(n+1));
			currentNode.nextNode(NextNode);
			myQuickRef.add(NextNode);
			currentNode=currentNode.nextNode;
			n++;
		}
		myQuick=myQuickRef;
		//After this the nodes are effectively in a linked list. The head is the node "startPlace"
		
		//next we want to calculate distances between all the points and store them in an array of arrays
		double[][] myDistanceChart=new double[total][total];
		for(int x=0; x<total;x++){
			for(int y=0;y<total;y++){
				myDistanceChart[x][y]=dist(myQuickRef.get(x),myQuickRef.get(y));
			}
			
		}
		//next we want to find the biggest distance
		double biggestdist = 0;
		int bigX = 0;
		int bigY = 0;
		for(int x=0; x<total;x++){
			for(int y=0;y<total;y++){
				if(myDistanceChart[x][y]>biggestdist){
					bigX=x;
					bigY=y;
					biggestdist=myDistanceChart[x][y];
				}
			}
			
		}
		for(int z=0;z<myQuick.size();z++){
		}
		
		Node NodeX=myQuickRef.get(bigX);
		Node NodeY=myQuickRef.get(bigY);
		
		/* Next few lines used for quick test of code so far used in main 	
		System.out.println(NodeX.name);
		System.out.println(NodeY.name);
		*/
		
		double[] midpoint=midpoint(NodeX,NodeY);
		Node holderNode=startPlace;
		//System.out.println("X midpointis "+midpoint[0]+" and Y midpoint is "+midpoint[1]);
		while(holderNode!=null){
			normalizeDot(holderNode,midpoint);
			//System.out.println(holderNode.name);
			holderNode=holderNode.nextNode;
		}
		bootOrigin(startPlace);
		//System.out.println("Mr smith's is "+ myQuick.get(2).normalizedX);
		int[] myRight=makeRight(startPlace);
		//System.out.println("myRight is"+myRight.length+" units long");
		/*
		for(int k=0;k<myRight.length;k++){
			System.out.println(k+"th element of Right is"+myRight[k]);
		}
		*/
		int[] myLeft=makeLeft(startPlace);
		/*
		for(int k=0;k<myLeft.length;k++){
			System.out.println(k+"th element of Left is"+myRight[k]);
		}
		System.out.println("I work"+myLeft.length);
		*/
		int[] myTopCenter=makeTopCenter(startPlace);
		int[] myBottomCenter=makeBottomCenter(startPlace);
		int[] myOrigin=makeOrigin(startPlace);
		ArrayList<Integer> myPath=generatePath(myLeft, myRight, midpoint, myTopCenter, myBottomCenter, myOrigin);
		//System.out.println("I work");
		//System.out.println(myPath.size());
		int r=0;
		for(int h=0;h<myPath.size();h++){
			Node nodeOn=myQuick.get(myPath.get(h));
			//System.out.println(myPath.size());
			System.out.println(nodeOn.name);
			r++;
		}
		//System.out.println("Final");
		
		
		//NOW CALCULATE PERCENTAGE WE CAN RUN
		double pathDistance=0;
		for(int w=0;w<(myPath.size()-1);w++){
			Node currentNode1=myQuick.get(myPath.get(w));
			//System.out.println(myPath.size());
			Node nextNode2=myQuick.get(myPath.get(w+1));
			pathDistance+=dist(currentNode1,nextNode2);
		}
		double percentage=maxMiles/pathDistance;
		percentage=percentage*100;
		if (percentage>100){
			percentage=100;
		}
		System.out.println(percentage+"%");
		System.out.println(r);
	}
	
	public static double dist(Node node1,Node node2){
		double xdist=node1.xcoords-node2.xcoords;
		double ydist=node1.ycoords-node2.ycoords;
		double mynum=((Math.pow(xdist,2))+Math.pow(ydist,2));
		return Math.sqrt(mynum);
	}
	public static double[] midpoint(Node node1, Node node2){
		double[] myAns=new double[2];
		myAns[0]=(node1.xcoords+node2.xcoords)/2;
		myAns[1]=(node1.ycoords+node2.ycoords)/2;
		return myAns;
	}
	//RIGHT, ORDER IN ASCENDING ORDER OF DEGREES
	public static void bootOrigin(Node head){
		Node trackerNode=head;
		while(trackerNode!=null){
			if((trackerNode.normalizedX==0)&(trackerNode.normalizedY==0)){
				trackerNode.inputNormalized(.00001,.0001);
			}
			trackerNode=trackerNode.nextNode;
		}
	}
	public static int[] makeRight(Node head){
		Node currNode=head;
		int[] myRight=new int[total];
		while(currNode!=null){
			int currNodeNumber=myQuick.indexOf(currNode);
			if(currNode.normalizedX>0){
				//System.out.println(currNode.name+" is being considered for Right");
				currNode.degrees(Math.atan(currNode.normalizedY/currNode.normalizedX));
				if (myRight[0]==0){
					myRight[0]=currNodeNumber;
					} else{
						int b=0;
						int t=0;
						int mostRecentUniqueDegree=0;
						while(b==0){
							if (myRight[t]==0){
								myRight[t]=currNodeNumber;
								b=1;
								}
							if(myQuick.get(myRight[t]).degrees>currNode.degrees){
								mostRecentUniqueDegree=t;
							}
							if(myQuick.get(myRight[t]).degrees==currNode.degrees){//IN CASE TWO POINTS HAVE SAME DEGREE GO TO CLOSEST FIRST
								double dist1=dist(myQuick.get(myRight[mostRecentUniqueDegree]),myQuick.get(myRight[t]));
								double dist2=dist(myQuick.get(myRight[mostRecentUniqueDegree]),currNode);
								if(dist1>dist2){
									insertNode(currNodeNumber,t,myRight);
								}
							}
							if(myQuick.get(myRight[t]).degrees<currNode.degrees){//MIGHT BE REVERSED?
								insertNode(currNodeNumber,t,myRight);
								b=1;
							}
							t++;
						}
				}
			}
			currNode=currNode.nextNode;
		}
		myRight=cleanUp(myRight);
		return myRight;
	}
	public static int[] makeOrigin(Node head){
		Node currNode=head;
		int[] myOrigin=new int[total];
		while(currNode!=null){
			int currNodeNumber=myQuick.indexOf(currNode);
			if((currNode.normalizedX==0)&(currNode.normalizedY==0)){
				System.out.println("This path may not be the best one to take. This code has not been fully optimized to consider places in the exact center of your destination.");
				myOrigin[0]=currNodeNumber;
			}
			currNode=currNode.nextNode;
		}
		return myOrigin;
	}
	public static int[] cleanUp(int[] array){
		int boo=0;
		int indexing=0;
		while(boo==0){
			if(array[indexing]==0){
				boo=1;
			}
			indexing++;
		}
		int[] myCleanedArray=new int[indexing-1];
		for(int n=0;n<myCleanedArray.length;n++){
			myCleanedArray[n]=array[n];
		}
		return myCleanedArray;
	}
	public static int[] makeLeft(Node head){
		Node currNode=head;
		int[] myLeft=new int[total];
		while(currNode!=null){
			int currNodeNumber=myQuick.indexOf(currNode);
			if(currNode.normalizedX<0){
				currNode.degrees(Math.atan(currNode.normalizedY/currNode.normalizedX));
				//System.out.println(currNode.name+" is being considered for left");
				if (myLeft[0]==0){
					myLeft[0]=currNodeNumber;
					} else{
						int b=0;
						int t=0;
						int mostRecentUniqueDegree=0;
						while(b==0){
							if (myLeft[t]==0){
								myLeft[t]=currNodeNumber;
								b=1;
								}
							if(myQuick.get(myLeft[t]).degrees>currNode.degrees){
								mostRecentUniqueDegree=t;
							}
							if(myQuick.get(myLeft[t]).degrees==currNode.degrees){//IN CASE TWO POINTS HAVE SAME DEGREE GO TO CLOSEST FIRST
								double dist1=dist(myQuick.get(myLeft[mostRecentUniqueDegree]),myQuick.get(myLeft[t]));
								double dist2=dist(myQuick.get(myLeft[mostRecentUniqueDegree]),currNode);
								if(dist1>dist2){
									insertNode(currNodeNumber,t,myLeft);
								}
							}
							if(myQuick.get(myLeft[t]).degrees<currNode.degrees){
								insertNode(currNodeNumber,t,myLeft);
								b=1;
							}
							t++;
						}
				}
			}
			currNode=currNode.nextNode;
		}
		myLeft=cleanUp(myLeft);
		return myLeft;
	}
	
	public static int[] makeTopCenter(Node head){
		Node currNode=head;
		int[] myTopCenter=new int[total];
		while(currNode!=null){
			int currNodeNumber=myQuick.indexOf(currNode);
			if((currNode.normalizedX==0)&(currNode.normalizedY>0)){
				if (myTopCenter[0]==0){
					myTopCenter[0]=currNodeNumber;
					} else{
						int b=0;
						int t=0;
						while(b==0){
							if (myTopCenter[t]==0){
								myTopCenter[t]=currNodeNumber;
								b=1;
								}
							
							if(myQuick.get(myTopCenter[t]).normalizedY>currNode.normalizedY){
									insertNode(currNodeNumber,t,myTopCenter);
								
							}
							t++;
						}
				}
			}
			currNode=currNode.nextNode;
		}
		myTopCenter=cleanUp(myTopCenter);
		return myTopCenter;
	}
	public static int[] makeBottomCenter(Node head){
		Node currNode=head;
		int[] myBottomCenter=new int[total];
		while(currNode!=null){
			int currNodeNumber=myQuick.indexOf(currNode);
			if((currNode.normalizedX==0)&(currNode.normalizedY<0)){
				if (myBottomCenter[0]==0){
					myBottomCenter[0]=currNodeNumber;
					} else{
						int b=0;
						int t=0;
						while(b==0){
							if (myBottomCenter[t]==0){
								myBottomCenter[t]=currNodeNumber;
								b=1;
								}
							
							if(myQuick.get(myBottomCenter[t]).normalizedY<currNode.normalizedY){
									insertNode(currNodeNumber,t,myBottomCenter);
								
							}
							t++;
						}
				}
			}
			currNode=currNode.nextNode;
		}
		myBottomCenter=cleanUp(myBottomCenter);
		return myBottomCenter;
	}
	
	//DO NOT ATTEMPT TO FILL A FULL ARRAY
	//POS is the actual indexing number (ie start from 0)
	public static void insertNode(int nodeNum,int pos, int[] myArray){
		int n=myArray.length;
		n=n-1;
		while(n>(pos+1)){
			myArray[n-1]=myArray[n-2];
			n--;
		}
		myArray[pos]=nodeNum;
	}
	
    //CAN'T DO ATAN(0) SO WE HAVE TO MANUALLY CREATE ALL EXCEPTIONS/IF STATEMENTS FOR EVERY TIME THE Y COORD IS 0
    
    
    public static void normalizeDot(Node inputNode, double[] midpoint){
        //PROBABLY SHOULD MAKE A NEW FIELD IN NODE WITH NEW EFFECTIVE NODE????
        double midX=midpoint[0];
        double midY=midpoint[1];
        double normalizedX=inputNode.xcoords-midX;
        //System.out.println(inputNode.name+"'s normalized X is "+inputNode.normalizedX);
        double normalizedY=inputNode.ycoords-midY;
        inputNode.inputNormalized(normalizedX, normalizedY);
        //MAKE A NEW CONSTRUCTOR METHOD FOR THE NODES THAT ALLOWS YOU TO PASS IN THESE NEW NORMALIZED VALUES
        
    }
    
    public static ArrayList<Integer> generatePath(int [] Left,int[] Right, double[] midpoint, int[] TopCenter, int[] BottomCenter, int[] Origin){
    	int boo=0;//if the origin was successfully located
    	int side=0;//if set to 1, origin is on left half, if set to 2 origin on right,3 it's in origin, 4 TopCenter, 5 BottomCenter
    	int location=0;
    	ArrayList<Integer> myPath=new ArrayList<Integer>();
    	for(int n=0;n<Left.length;n++){
    		if(Left[n]==1){
    			side=1;
    			boo=1;
    			location=n;
    		}
    	}
    	for(int n=0;n<Right.length;n++){
    		if(Right[n]==1){
    			side=2;
    			boo=1;
    			location=n;
    		}
    	}
    	
    	for(int n=0;n<Origin.length;n++){
    		if(Origin[n]==1){
    			side=3;
    			boo=1;
    			location=n;
    		}
    	}
    	for(int n=0;n<TopCenter.length;n++){
    		if(TopCenter[n]==1){
    			side=4;
    			boo=1;
    			location=n;
    		}
    	}
    	for(int n=0;n<BottomCenter.length;n++){
    		if(Right[n]==1){
    			side=5;
    			boo=1;
    			location=n;
    		}
    	}
    	
    	if(side==1){//origin is on left side
    		for(int i=location;i<Left.length;i++){
    			myPath.add(Left[i]);
    		}
    		for(int j=0;j<TopCenter.length;j++){
    			myPath.add(TopCenter[j]);
    		}
    		for(int j=0;j<Right.length;j++){
    			myPath.add(Right[j]);
    		}
    		for(int j=0;j<BottomCenter.length;j++){
    			myPath.add(BottomCenter[j]);
    		}
    		for(int j=0;j<location;j++){
    			myPath.add(Left[j]);
    		}
    	}
    	if(side==2){//origin is on right side
    		for(int i=location;i<Right.length;i++){
    			myPath.add(Right[i]);
    		}
    		for(int j=0;j<BottomCenter.length;j++){
    			myPath.add(BottomCenter[j]);
    		}
    		for(int j=0;j<Left.length;j++){
    			myPath.add(Left[j]);
    		}
    		for(int j=0;j<TopCenter.length;j++){
    			myPath.add(TopCenter[j]);
    		}
    		for(int j=0;j<location;j++){
    			myPath.add(Right[j]);
    		}
    	}
    	//This next if statement is buggy
    	/*
    	if(side==3){//origin is origin
    		for(int i=location;i<Right.length;i++){
    			myPath.add(Right[i]);
    		}
    		for(int j=0;j<BottomCenter.length;j++){
    			myPath.add(BottomCenter[j]);
    		}
    		for(int j=0;j<Left.length;j++){
    			myPath.add(Left[j]);
    		}
    		for(int j=0;j<TopCenter.length;j++){
    			myPath.add(TopCenter[j]);
    		}
    		for(int j=0;j<location;j++){
    			myPath.add(Right[j]);
    		}
    	}
    	*/
    	if(side==4){//origin is on TopCenter
    		for(int i=location;i<TopCenter.length;i++){
    			myPath.add(TopCenter[i]);
    		}
    		for(int j=0;j<Right.length;j++){
    			myPath.add(Right[j]);
    		}
    		for(int j=0;j<BottomCenter.length;j++){
    			myPath.add(BottomCenter[j]);
    		}
    		for(int j=0;j<Left.length;j++){
    			myPath.add(Left[j]);
    		}
    		for(int j=0;j<location;j++){
    			myPath.add(TopCenter[j]);
    		}
    	}
    	if(side==5){//origin is on BottomCenter
    		for(int i=location;i<BottomCenter.length;i++){
    			myPath.add(BottomCenter[i]);
    		}
    		for(int j=0;j<Left.length;j++){
    			myPath.add(Left[j]);
    		}
    		for(int j=0;j<TopCenter.length;j++){
    			myPath.add(TopCenter[j]);
    		}
    		for(int j=0;j<Right.length;j++){
    			myPath.add(Right[j]);
    		}
    		for(int j=0;j<location;j++){
    			myPath.add(BottomCenter[j]);
    		}
    	}
    	myPath.add(1);
    	return myPath;
    }

    
    //PROBABLY MAKE ANOTHER FIELD ON THE NODES HOLDING THEIR DEGREE ON THEIR RESPECTIVE SIDES
    
  

	public static class LinkedList {

		Node currentNode;
		Node nextNode;

		public LinkedList(Node contains, Node nextNodeInput) {
			currentNode = contains;
			nextNode = nextNodeInput;
		}

	}

	public static class Node {

		double xcoords;
		double ycoords;
		String name;
		Node nextNode;
		double degrees;
		double normalizedX;
		double normalizedY;
		//int codename; <---probably don't need this
		//POTENTIAL PROBLEM WITH DOUBLES INSTEAD OF INTS???????????
		public void inputNormalized(double newX,double newY){
			this.normalizedX=newX;
			this.normalizedY=newY;
		}
		public Node(String info){
			int indexX;
			int indexY;
			
			indexX=info.indexOf(" ");
			indexY=(info.substring(indexX+1).indexOf(" "))+indexX+1;
			
			this.xcoords=Double.parseDouble(info.substring(0,indexX));
			this.ycoords=Double.parseDouble(info.substring(indexX+1,indexY));
			
			this.name=info.substring(indexY+1);
		}
		
		public void degrees(double deg){
			this.degrees=deg;
		}
		
		public Node(double x, double y, String place) {
			this.xcoords = x;
			this.ycoords = y;
			this.name = place;
		}

		public Node() {
			// TODO Auto-generated constructor stub
		}

		public void nextNode(Node inputNode){
			this.nextNode=inputNode;
		}
	
	
}
}