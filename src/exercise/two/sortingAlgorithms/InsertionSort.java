
public class InsertionSort {
	
	//instance variables
	
	//constructor
	public InsertionSort()
	{
		//empty
	}

	  /*Function to sort array using insertion sort*/
    public String[] sort(String arr[]) 
    { 
        int n = arr.length; 
        for (int i = 1; i < n; ++i) { 
            String key = arr[i]; 
            int j = i - 1; 
  
            /* Move elements of arr[0..i-1], that are 
               greater than key, to one position ahead 
               of their current position */
            while (j >= 0 && arr[j].compareToIgnoreCase(key) > 0) { 
                arr[j + 1] = arr[j]; 
                j = j - 1; 
            } 
            arr[j + 1] = key; 
        } 
        return arr;
    } 
    
    
}
