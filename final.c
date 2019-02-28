#include<stdio.h>
#include <stdlib.h>

int main()
{

int c;
FILE *fp = fopen("data.txt", "r");

  int var1[1000], n, count, remain, flag=0,time_quantum;
  float var2[1000], var3[1000], time, wait_time=0, turnaround_time=0;


  printf("Enter Total Process:\t ");
  scanf("%d",&n);
  remain=n;

//store arrival time and amount of time process requires to complete
while ((c = fgetc(fp)) != EOF) {
   
int count = 0;
    fscanf(fp, "%d%f",&var1[count], &var2[count]);
    var3[count]=var2[count];

	count++;
  }


  printf("Enter Time Quantum:\t");
  scanf("%d",&time_quantum);
  printf("\n\nProcess\t|Turnaround Time|Waiting Time\n\n");

//all processes one by one repeatedly
  for(time=0,count=0;remain!=0;)
  {
    if(var3[count]<=time_quantum && var3[count]>0)
    {

      time+=var3[count];
      var3[count]=0;
      flag=1;
    }
// Decrease burst_time by quantum
    else if(var3[count]>0)
    {
      var3[count]-=time_quantum;
      time+=time_quantum;
    }
//Calculate wait time and turnaround time
    if(var3[count]==0 && flag==1)
    {
      remain--;
      printf("P[%d]\t|\t%f\t|\t%f\n", count+1, time-var1[count],time-var1[count]-var2[count]);
      wait_time+=time-var1[count]-var2[count];
      turnaround_time+=time-var1[count];
      flag=0;
    }
    if(count==n-1)
      count=0;

    else if(var1[count+1]<=time)
      count++;

    else
      count=0;
// If all processes are done break;
  }
printf("%f\n", wait_time);
printf("%f\n", turnaround_time);

  printf("\nAverage Waiting Time= %f\n",wait_time/n);
  printf("Avg Turnaround Time = %f",turnaround_time/n);
  
fclose(fp);
  return 0;
}