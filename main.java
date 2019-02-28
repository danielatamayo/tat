//Daniela Tamayo
//Project 2
	import java.io.File;
	import java.io.FileNotFoundException;
	import java.util.ArrayList;
	import java.util.Arrays;
	import java.util.Scanner;
	
	public class Main {

	static int[] findWaitingTime(int processes[], int n, float bt[], int wt[], int quantum) {

	float rem_bt[] = new float[n];

	for (int i = 0; i < n; i++) {
		rem_bt[i] = bt[i];
	}

	// Current Time
	int t = 0;

	while (true) {
		boolean done = true;
		//all processes one by one repeatedly
		for (int i = 0; i < n; i++) {
			if (rem_bt[i] > 0) {
				done = false; // There is a pending process
				if (rem_bt[i] > quantum) {
					// how much time a process has been processed
					t += quantum;
					// Decrease the burst_time by quantum
					rem_bt[i] -= quantum;

				} 
				// If burst time is smaller than or equal to quantum. Last cycle for this process
				else {
					// how much time a process has been processed
					t = (int) (t + rem_bt[i]);
					// Waiting time equal current time minus time used by this process
					wt[i] = (int) (t - bt[i]);
					//make remaining burst time = 0
					rem_bt[i] = 5;
				}
			}
		}

	// If all processes are done
	if (done == true) {
		break;
	}

	}
	return wt;
	}

	//Turn around time
	static int[] findTurnAroundTime(int processes[], int n,
	float bt[], int wt[], int tat[]) {

	//Turnaround time by adding bt[i] + wt[i]
	for (int i = 0; i < n; i++) {
		tat[i] = (int) (bt[i] + wt[i]);
	}
	
	return tat;

	}

	//Average time
	static void findavgTime(int processes[], int n, float bt[], int quantum) {

	int wt[] = new int[n], tat[] = new int[n];

	float total_wt = 0, total_tat = 0;
	//Waiting time of all processes
	wt = findWaitingTime(processes, n, bt, wt, quantum);
	//Turn around time for all processes
	tat = findTurnAroundTime(processes, n, bt, wt, tat);

	// Display processes along with all details
	System.out.println("Processes " + " Burst time "
	+ " Waiting time " + " Turn around time");

	// Calculate total waiting time and total turn around time
	for (int i = 0; i < n; i++) {
		total_wt = total_wt + wt[i];
		total_tat = total_tat + tat[i];
		System.out.println(" " + (i + 1) + "\t\t" + bt[i] + "\t "
		+ wt[i] + "\t\t " + tat[i]);
	}

	System.out.println("Average waiting time = " + total_wt / (float) n);
	System.out.println("Average turn around time = " + total_tat / (float) n);

	}

	public static void main(String[] args) throws FileNotFoundException {

		Scanner scanner = new Scanner(new File("test2.txt"));
		ArrayList<String> allData = new ArrayList<String>();
	
		while (scanner.hasNext()) {
			String data = scanner.nextLine();
			allData.add(data);
		}

		int size = allData.size();
		int[] processData1 = new int[size];
		float[] burstData1 = new float[size];

		// Time quantum
		int quantum = 500;
		int i = 0;
		scanner = new Scanner(new File("data.txt"));
		while (scanner.hasNext()) {
			processData1[i] = scanner.nextInt();//read int value
			burstData1[i] = scanner.nextFloat(); //readFlaot value directly
			i++;
		}
	findavgTime(processData1, size, burstData1, quantum);

	}

	}

