import java.util.*;

public class Klists{
	
	public Klists(){};

	public double [] mergeKLists(double [][] outerArray){
		int totalSize=0;
		for(int i=0;i<outerArray.length;i++){//sums up all the lengths of each list of numbers in outerArray
			totalSize=totalSize+outerArray[i].length;
		}

		double [] outputArray=new double[totalSize];

		if(outerArray.length>1){//only runs if there are more than 1 value to be sorted
			double [] left=new double[outerArray[0].length];//sets left to be the first array in outerArray
			copyArray(outerArray[0], left);
			double [] right=new double[outerArray[1].length];//sets right to the second array in outerArray
			copyArray(outerArray[1],right);
			int index=1;
			double [] temp;//we need a temp array to hold intermediate values when we merge
			while(left.length+right.length<totalSize){
				temp=new double[left.length+right.length];//temp will be the merged array
				copyArray(merge(left, right),temp);//merge left and right into temp
				left=new double[temp.length];//make a new left array 
				copyArray(temp,left);// copy temp into left
				right=new double[outerArray[index+1].length];//the new right array is the next array in outerArray
				copyArray(outerArray[index+1],right);

			}
			copyArray(merge(left,right),outputArray);//merge last two arrays
			return outputArray;
		}
		else{
			return outerArray[0];
		}

	}

	public void printArray(double [] arr){//prints array 
        int i;
        System.out.println();
		for(i =0;i<arr.length-1;i++){
			System.out.print(arr[i]+", ");
		}
		System.out.print(arr[i]);
		System.out.println();
		
	}

	public void copyArray(double [] arr1, double [] arr2){//copies values in array 1 to array 2

		for(int i=0;i<arr1.length;i++){
			arr2[i]=arr1[i];
		}
	}
	

	public double [] merge(double [] left, double [] right){
		double [] returnArray=new double[left.length+right.length];
        
		int i=0;
		int l=0, r=0;
		while(l<left.length && r<right.length){
			if(left[l]<=right[r]){
				returnArray[i]=left[l];
				l++;
				i++;
			}
			else if(right[r]<left[l]){
				returnArray[i]=right[r];
				r++;
				i++;
			}
		}
        
		while(l<left.length){
			returnArray[i]=left[l];
			i++;
			l++;
		}

		while(r<right.length){
			returnArray[i]=right[r];
			i++;
			r++;
		}

		return returnArray;
	}

	public static void main(String [] args){
		Klists k = new Klists();
		Scanner scanner=new Scanner(System.in);

		System.out.println("Enter amount of arrays you want to merge: ");
		int amt = scanner.nextInt();
         
        
		double [][] arr = new double [amt][];
		int i = 0; 
		while(i < amt){
			System.out.println("Enter length of Sub Array: ");
			int arrlen=scanner.nextInt();
			arr[i]=new double[arrlen];
			System.out.println("Enter values for Array " + i);
			for(int c = 0; c < arr[i].length; c++){
				arr[i][c]=scanner.nextDouble();
			}
			i++;
		}

		double [] output=k.mergeKLists(arr);
		k.printArray(output);
	}
}