#include<iostream>
#include"./sort/sort1.h"
using namespace std;

int main(int argc, char *argv[])
{
    Sort1 *sort = new Sort1();
    vector<int> arr{ 13, 65, 97, 76, 38, 27, 49 };
    sort->MergeSort(arr, 0, arr.size() - 1);
    int len = arr.size();
    for(vector<int>::const_iterator iter = arr.begin(); iter != arr.end(); ++iter)
    {
        cout << (*iter) <<endl;
    }
    return 0;
}