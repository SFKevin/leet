#ifndef __SORT_H
#define __SORT_H
#include<vector>
using namespace std;

class Sort
{
public:
    Sort();
    ~Sort();
    void selectSort(vector<int> &a);
    void insertSort(vector<int>& a);
    void bubbleSort(vector<int>& a);
    void quickSort(vector<int>& a, int low, int high);
    void shellSort(vector<int>& a);
    void merge(vector<int>& a, int p, int q, int r);
    void mergeSort(vector<int>& a, int p, int r);
    int leftChild(int i);
    void adjustHeap(vector<int>& a, int i, int n);
    void swap(vector<int>& a, int index1, int index2);
    void heapSort(vector<int>& a);
};

#endif