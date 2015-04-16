Done in Eclipse
=====================================================================================================================================================

The problem I was given was(paraphrasing):

Your task is to figure out the shortest path given a list of destinations in a 2-D coordinate system. You will be given a starting destination which you must also end at. You will also be given a number that represents the number of miles that we can expect to run(this is because Rally is a company that encourages fitness), and you must also calculate the percentage of the path that we can expect to run.

For example, if you are given as your input:
"4"	//Number of places that will be entered
"0 0 office" //starting point "office" is at point 0,0
"1 1 mr-smiths"
"0 1 baked-and-wired"
"1 0 amc-georgetown"
"5" //number of miles we expect to be able to run



Your output should be(or similar to):
"office" //starting destination
"amc-georgetown"
"mr-smiths"
"baked-and-wired"
"office" //you must end where you started
"100%" 
"4" //number of places entered

===================================================================================================================================================

The approach I took was reasoning out that the shortest path would be in the form of a circle. I would simply calculate each destination's "degree" with respect to the origin and start from the angle pi/2 (radians) and go around the imaginary circle clockwise and then by the time I reached each destination at least once, I should be very close to the start/end point.

I also tried optimizing the path by taking into account things like two points having the same "degree" but having different radii from the origin. For example if I had to choose between points (1,1) and (100,100) and I was at (100,0) I would first go to (100,100) because it is closer to my current location, then go down to (1,1).

Looking back I realize that this would not always give the optimal path. If I was given the points:

(0,0)
(100,100)
(2,1)
(3,0)
(100,-100)

I would output:

(0,0)
(100,100)
(2,1)
(3,0)
(100,-100)

which is a zigzag(with alot of extra distance traveling from (100,100) to (3,0) to (100,-100))

when the optimal path would probably be:

(0,0)
(2,1)
(3,0)
(100,100)
(100,-100)


However this algorithm worked for most of my hypothetical test cases and so I was the algorithm I choose to implement.



=====================================================================================================================================

Testing:


This was the test case they gave me:

/*The myInputs.add are present because I wasn't sure how they wanted to me handle input so I decided to simply enter it directly into the main method. i.e they are from my code */

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


This was my output: 

office
whole-foods-2
mr-smiths
baked-and-wired
detroit
no
gold-club
ok-city
cento
21st-amendment
alexander-steakhouse
cleveland
chicken-and-waffles
mcdonalds-1
boise
el-paso
chicago
sushirito
dallas
nyc
atlanta
juneau
delfina
honolulu
chilatown
huntsville
district
austin
workshop
buffalo
amc-georgetown
office
19.3200141660933%
32


Which I suppose is reasonable.

