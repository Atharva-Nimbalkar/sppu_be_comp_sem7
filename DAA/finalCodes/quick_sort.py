import random
import time
def deterministic_partition(arr,low,high):
    pivot=arr[low]
    i=low+1
    for j in range(low+1,high+1):
        if arr[j]<pivot:
            arr[i],arr[j]=arr[j],arr[i]
            i+=1
    arr[low],arr[i-1]=arr[i-1],arr[low]
    return i-1

def randomized_partition(arr,low,high):
    pivot=random.randint(low,high)
    arr[pivot],arr[low]=arr[low],arr[pivot]
    return deterministic_partition(arr,low,high)

def deterministic_quicksort(arr,low,high):
    if low<high:
        pivot=deterministic_partition(arr,low,high)
        print(f"Pivot element is : {arr[pivot]}")
        print(f"Array after partiotioning with {arr[pivot]} : {arr}")
        print("\n")
        deterministic_quicksort(arr,low,pivot-1)
        deterministic_quicksort(arr,pivot+1,high)
    
def randomized_quicksort(arr,low,high):
    if low<high:
        pivot=randomized_partition(arr,low,high)
        print(f"Pivot element is : {arr[pivot]}")
        print(f"Array after partiotioning with {arr[pivot]} : {arr}")
        print("\n")
        randomized_quicksort(arr,low,pivot-1)
        randomized_quicksort(arr,pivot+1,high)
    
n=int(input("Enter the no. of elements : "))
list=[]
for i in range(n):
    list.append(int(input("Enter the elements : ")))
print("*"*50)
start_time=time.time()    
deterministic_quicksort(list.copy(),0,len(list)-1)
end_time=time.time()-start_time
print("Deterministic Quick Sort Time",end_time)
print("*"*50)
start=time.time()
randomized_quicksort(list.copy(),0,len(list)-1)
end=time.time()-start
print("Randomized Quick Sort Time",end)