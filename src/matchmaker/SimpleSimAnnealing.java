package matchmaker;

public class SimpleSimAnnealing implements Solver {

    public static final double INIT_PROB = 0.95; // initial probability of accepting a higher cost
    public static final int FREEZE_LIM = 10;//number of times run trials to accept a minimum cost
    public static final int TRIALS = 800;//number of perturbations, We have eighty colors/rooms
    public static final int CHANGES = 100;//exhaust solution space the solution space
    public static final int N = 1000;//solution space/number of annealing to run
    public static final double COOL_RATIO = 0.95;//???
    public static final double INIT_TEMP = 100;//???
    public static final double MIN_PERCENT = 0.01;// if changes/trials terminate
    public int bestSolution = 0;

    //generic simulated annealing method
    public double solve(Graph classes, Room[] rooms){
	initialSolution(classes, rooms);
	double temp = INIT_TEMP;
	int freezeCount = 0;
	while(freezeCount < FREEZE_LIM){
	    int trials = 0;
	    int changes = 0;
	    while(trials <= TRIALS*N && changes <= CHANGES){
		trials++;
		double deltaCost = nextChange(classes, rooms);
		int lastChange = classes.lastChange();
		if(deltaCost<=0){//down hill accept change
		    changes++;
		    bestSolution = lastChange;
		}
		else{//uphill
		    double r = Math.random();
		    if (r > Math.pow(Math.E,-deltaCost/temp)){//decline next change with decreasing probability
			changes++;
			changeSolution(classes, lastChange);//Undo change
		    }
		}
		temp = temp*COOL_RATIO;
	    }//trials loop
	    if(changes/trials < MIN_PERCENT) break;
	}//freeze loop
	return new SimpleCost().eval(classes);
    }

    //get initial Solution
    public void initialSolution(Graph classes, Room[] rooms) {
	(new SimpleSoluGen()).generate(classes, rooms);
    }

    //make a next change/perturbation return cost of change
    public double nextChange(Graph current, Room[] rooms){
        // Find a random class and assign a random room
        int randomClass = (int) Math.random()*current.getNodes().length;
        int randomRoom = (int) Math.random()*rooms.length;
        int journalEntry = current.setRoom(current.getNodes()[randomClass], rooms[randomRoom]);
        return new SimpleCost().deltaEval(current, current.getNodes()[randomClass], rooms[randomRoom]);
    }

    //Undo last change if change not accepted 
    public void changeSolution(Graph current, int change){
	current.undo(change);
    }

    //output undo steps made since last best silution
    public void finalSolution(Graph current, int bestSolution){	
	current.undo(bestSolution);
    }
}	
