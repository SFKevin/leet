#include<iostream>
#include"./sort/sort.h"
using namespace std;

int main(int argc, char *argv[])
{
    Sort *sort = new Sort();
    vector<int> arr{ 13, 65, 97, 76, 38, 27, 49 };
    sort->heapSort(arr);
    int len = arr.size();
    for(vector<int>::const_iterator iter = arr.begin(); iter != arr.end(); ++iter)
    {
        cout << (*iter) <<endl;
    }
    return 0;
}