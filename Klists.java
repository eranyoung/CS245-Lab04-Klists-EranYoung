import java.util.*;

public class Klists{
	
	public Klists(){};

	public double [] mergeKLists(double [][] outerArray){
		int totalSize=0;
		for(int i=0;i<outerArray.length;i++){
			totalSize=totalSize+outerArray[i].length;
		}

		double [] outputArray=new double[totalSize];

		if(outerArray.length>1){
			double [] left=new double[outerArray[0].length];
			copyArray(outerArray[0], left);
			double [] right=new double[outerArray[1].length];
			copyArray(outerArray[1],right);
			int index=1;
			double [] temp;
			while(left.length+right.length<totalSize){
				temp=new double[left.length+right.length];
				copyArray(merge(left, right),temp);
				left=new double[temp.length];
				copyArray(temp,left);
				right=new double[outerArray[index+1].length];
				copyArray(outerArray[index+1],right);
			}
			copyArray(merge(left,right),outputArray);
			return outputArray;
		}
		else{
			return outerArray[0];
		}

	}

	public void printArray(double [] arr){
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
         
        System.out.println("Enter length of Arrays: ");
        int arrlen=scanner.nextInt();
        
		double [][] arr = new double [amt][arrlen];
		int i = 0; 
		while(i < amt){
			System.out.println("Enter values for Array " + i);
			for(int c = 0; c < arrlen; c++){
				arr[i][c]=scanner.nextDouble();
			}
			i++;
		}
		/*System.out.println("How many arrays would you like to merge: ");
		int size=scanner.next();

		double [][] arr=new double [size][];*/

		double [] output=k.mergeKLists(arr);
		k.printArray(output);
	}
}