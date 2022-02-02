#include <stdio.h>
#include "stdlib.h"
void swap(int *a,int *b)
{
    int temp = *a;
    *a=*b;
    *b=temp;
}
void heapify(int arr[], int n, int i)
{
    int largest = i;
    int l = 2 * i + 1;
    int r = 2 * i + 2;
    if (l < n && arr[l] > arr[largest])
        largest = l;
    if (r < n && arr[r] > arr[largest])
        largest = r;
    if (largest != i) {
        swap(arr+i, arr+largest);
        heapify(arr, n, largest);
    }
}
void bottomUpHeapify(int arr[],int n,int i)
{
    int p = (i)/2;
    if(i>=0 && i<= (n-1))
    {
        if(arr[i]>arr[p])
        {
            swap(arr+i,arr+p);
            bottomUpHeapify(arr,n,p);
        }
    }
}
//void heapSort(int arr[], int n)
//{
//    for (int i = n / 2 - 1; i >= 0; i--)
//    {
//        heapify(arr, n, i);
//    }
//    for (int i = n - 1; i > 0; i--) {
//        swap(arr+0, arr+i);
//        heapify(arr, i, 0);
//    }
//}
void print(int arr[], int n)
{
    for (int i = 0; i < n; ++i)
    {
        printf("%d ",arr[i]);
    }
}
//int countSize(char string[5000])
//{
//    char first[500];
//    int count=0;
//    int j=0;
//    int f=0;
//    while (string[j]!='\0' || string[j]!='\n')
//    {
//        first[f]=string[j];
//        j++;
//        f++;
//        if(string[j]==' ')
//        {
//            first[f]='\0';
//            count++;
//            f=0;
//            j++;
//        }
//        else if(string[j]=='\0' || string[j]=='\n')
//        {
//            first[f]='\0';
//            count++;
//            break;
//        }
//    }
//    return count;
//}
void buildHeap(int arr[], int n)
{
    int startIdx = (n / 2) - 1;
    for (int i = startIdx; i >= 0; i--) {
        heapify(arr, n, i);
    }
}

//void start(int n,char inputs[n][5000])
//{
////    int size = 0;
////    for (int i = 0; i < n; ++i) {
////        size+=countSize(inputs[i]);
////    }
//    int mix[7000000];
//    int z=0;
//    for (int i = 0; i < n; ++i) {
//        char first[500];
//        int j=0;
//        int f=0;
//        while (inputs[i][j]!='\0' && inputs[i][j]!='\n')
//        {
//            first[f]=inputs[i][j];
//            j++;
//            f++;
//            if(inputs[i][j]==' ')
//            {
//                first[f]='\0';
//                mix[z]=atoi(first);
//                f=0;
//                j++;
//                z++;
//            }
//            else if(inputs[i][j]=='\0' || inputs[i][j]=='\n')
//            {
//                first[f]='\0';
//                mix[z]=atoi(first);
//                z++;
//            }
//        }
//    }
//    heapSort(mix,z);
//    print(mix,z);
//}
void delete(int key,int arr[],int n)
{
    int j=0;
    for (int i = 0; i < n; ++i) {
        if(arr[i]==key)
        {
            j=i;
            arr[i]=arr[n-1];
            break;
        }
    }
    n=n-1;
    int p= j/2;
    if(arr[p]>arr[j]){
        heapify(arr,n,j);
    }
    else
    {
       bottomUpHeapify(arr,n,j);
    }
}
void insert(int data ,int arr[],int n)
{
    n++;
    arr[n-1]=data;
    bottomUpHeapify(arr,n,n-1);
}
void change(int key,int data , int arr[],int n)
{
    for (int i = 0; i < n; ++i) {
        if(arr[i]==key)
        {
            if(data >arr[i])
            {
                arr[i]=data;
                bottomUpHeapify(arr,n,i);
            }
            else
            {
                arr[i]=data;
                heapify(arr,n,i);
            }
        }
    }
}
int main() {
//    int k ;
//    scanf("%d\n",&k);
//    char inputs[k][5000];
//    for (int i = 0; i < k; ++i) {
//        fgets(inputs[i],5000,stdin);
//    }
//    start(k,inputs);

  int arr[10] = { 1,5,6,2,3,4,7,9,10,11};
  buildHeap(arr,10);
  //delete(6,arr,10);
  //insert(20,arr,10);
//  print(arr,10);
  //printf("\n");
 // change(7,20,arr,10);
  delete(6,arr,10);
  print(arr,9);

    return 0;
}
