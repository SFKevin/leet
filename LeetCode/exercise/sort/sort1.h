#ifndef __SORT1_H
#define __SORT1_H
#include<vector>
using namespace std;
class Sort1
{
public:
    Sort1();
    ~Sort1();
    void SelectSort(vector<int> &a);
    void InsertSort(vector<int> &a);
    void ShellSort(vector<int> &a);
    void QuickSort(vector<int>& a, int low, int high);
    void Merge(vector<int>& a, int p, int q, int r);
    void MergeSort(vector<int>& a, int p,int r);
};

#endif