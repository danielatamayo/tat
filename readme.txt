Turn Around Time: Time Difference between completion time and arrival time. Turn Around Time = Completion Time – Arrival Time

Waiting Time(W.T): Time Difference between turn around time and burst time.
Waiting Time = Turn Around Time – Burst Time

*different arrival times

Create an array rem_bt[] to keep track of remaining burst time of processes. (a copy of bt[] burst times array)
Create another array wt[] to store waiting times of processes. Initialize to 0.
Initialize current time : t = 0
Keep traversing the all processes while all processes are not done. 
   If rem_bt[i] > quantum
       t = t + quantum
       bt_rem[i] -= quantum;
   Else // Last cycle for this process
       t = t + bt_rem[i];
       wt[i] = t - bt[i]
       bt_rem[i] = 0; // This process is over

Once we have waiting times, we can compute turn around time (tat) of a process as sum of waiting and burst times (wt[i] + bt[i])